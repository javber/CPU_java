package tp.pr5.mv;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import tp.pr5.mv.instrucciones.Instruction;
import tp.pr5.mv.instrucciones.operacionesBinarias.*;
import tp.pr5.mv.instrucciones.operacionesUnarias.*;
import tp.pr5.mv.instrucciones.otras.*;
import tp.pr5.mv.instrucciones.salto.Bf;
import tp.pr5.mv.instrucciones.salto.Bt;
import tp.pr5.mv.instrucciones.salto.Jump;
import tp.pr5.mv.instrucciones.salto.JumpInd;
import tp.pr5.mv.instrucciones.salto.Rbf;
import tp.pr5.mv.instrucciones.salto.Rbt;
import tp.pr5.mv.instrucciones.salto.Rjump;
import tp.pr5.mv.modulos.CPU;
import tp.pr5.mv.modulos.ProgramMV;
import tp.pr5.mv.modulos.controladores.ControladorConsola;
import tp.pr5.mv.modulos.controladores.ControladorSwing;
import tp.pr5.mv.modulos.streams.FileInMethod;
import tp.pr5.mv.modulos.streams.FileOutMethod;
import tp.pr5.mv.modulos.streams.InMethod;
import tp.pr5.mv.modulos.streams.NullInMethod;
import tp.pr5.mv.modulos.streams.NullOutMethod;
import tp.pr5.mv.modulos.streams.OutMethod;
import tp.pr5.mv.modulos.streams.StdInMethod;
import tp.pr5.mv.modulos.streams.StdOutMethod;
import tp.pr5.mv.ordenes.CommandInterpreter;
import tp.pr5.mv.ordenes.dosParametros.WriteCommand;
import tp.pr5.mv.ordenes.sinParametros.*;
import tp.pr5.mv.ordenes.unParametro.PushCommand;
import tp.pr5.mv.ordenes.unParametro.StepsCommand;
import tp.pr5.mv.parsers.*;
import tp.pr5.mv.parsers.CmdLineParser.ModeOptionEnum;
import tp.pr5.mv.parsers.CmdLineParser.Option;
import tp.pr5.mv.parsers.CmdLineParser.OptionException;
import tp.pr5.mv.parsers.CmdLineParser.Option.*;
import tp.pr5.mv.vistas.consola.VistaConsola;
import tp.pr5.mv.vistas.consola.VistaConsolaBatch;
import tp.pr5.mv.vistas.consola.VistaConsolaInteractive;
import tp.pr5.mv.vistas.swing.VistaSwing;

/*
 * Comando extra "RESET" para reiniciar el programa.
 * Ayudaba para debugear la MV. (podría ser útil un comando que reiniciara la MV
 * completamente)
 * 
 * Modo extra "DEBUG" para probar a ejecutar con vista interactiva y swing a la vez.
 * Funciona bastante bien, aunque en la vista interactiva aparecen algunos fallos gráficos
 * y situaciones no deseables cuando se apaga la cpu desde la vista swing o aparecen errores
 * en la cpu por la ejecución de la vista interactiva, ya que muestra ventanas de notificación.
 */


public class Main {

	private static Scanner entrada;
	
	// me da warnings por no usar las variables a las que están asignadas las vistas
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		// parser
		CmdLineParser argScan = new CmdLineParser();
		Option help,asm,in,mode,out;
		String fichPrograma, fichEntrada, fichSalida;
		ModeOptionEnum execModeOption;
		
		// modelo
		CPU cpu;
		ProgramMV programa = null;
		InMethod input = null;
		OutMethod output = null;
		
		// Vistas y controladores
		VistaSwing vistaSwing;
		VistaConsola vistaConsola;
		ControladorSwing controladorSwing;
		ControladorConsola controladorConsola;
		
		entrada = new Scanner(System.in);
		
		
		// Configurar el "Command parser"
		CommandParser.ParserConfig(new CommandInterpreter[] {
				new QuitCommand(), new RunCommand(), new StepCommand(),
				new StepsCommand(0), new PushCommand(0), new PopCommand(),
				new WriteCommand(0,0), new RstProgCommand() });

		// Configurar el "Instruction parser"
		InstructionParser.ParserConfig(new Instruction[] { new Add(),
				new Sub(), new Mul(), new Div(), new And(), new Or(), new Eq(),
				new Flip(), new Gt(), new Le(), new Ln(), new Push(0),
				new Dup(), new Not(), new Pop(), new Out(), new In(), new Halt(),
				new Load(0), new Store(0), new Bf(0), new Bt(0), new Jump(0),
				new Rjump(0), new Rbf(0), new Rbt(0), new StoreInd(), new LoadInd(),
				new JumpInd() });

		// Configurar el "CmdLine scanner"
		help = argScan.addBooleanOption('h', "help");
		asm  = argScan.addStringOption('a', "asm");
		in   = argScan.addStringOption('i', "in");
		mode = argScan.addOption(new ModeOption());
		out  = argScan.addStringOption('o', "out");
	
		// Intento parsear los argumentos de la llamada principal
		try{
			argScan.parse(args);
		}
		catch(OptionException e){
			errorExit(1, "Uso incorrecto: " + e.getMessage() + ".\nUse -h/--help para más detalles.");
		}
		
		// Si se ha usado el argumento "-h/--help" muestra las instruciones y sale
		if( argScan.getOptionValue(help)!=null ){
			mostrarUsage();
			entrada.close();
			System.exit(0);
		}
		
		// Comprueba si está en modo BATCH o INTERACTIVE para obtener el programa ASM y
		// configurar la entrada y la salida según sea necesario.
		execModeOption = (ModeOptionEnum)argScan.getOptionValue(mode);
		if(execModeOption == null)
			execModeOption = ModeOptionEnum.BATCH;
		fichPrograma = (String)argScan.getOptionValue(asm);
		fichEntrada = (String)argScan.getOptionValue(in);
		fichSalida = (String)argScan.getOptionValue(out);
		
		// Si está en modo BATCH y no se especifica un fichero ".asm", termina.
		if((execModeOption == ModeOptionEnum.BATCH || execModeOption == ModeOptionEnum.WINDOW)&& fichPrograma == null){
			errorExit(1, "Uso incorrecto: Fichero ASM no especificado.\nUse -h/--help para más detalles.");
		}
		
		// prepara todos los bloques del programa y lanza la cpu, que notificará a la vista
		switch(execModeOption){
			case WINDOW:
				input = configurarEntrada(fichEntrada, new NullInMethod());
				output = configurarSalida(fichSalida, new NullOutMethod());
				programa = obtenerPrograma(fichPrograma);
				cpu = new CPU(null, null);
				controladorSwing = new ControladorSwing(cpu);
				vistaSwing = new VistaSwing(controladorSwing, input, output);
				controladorSwing.start(programa);
				break;
			case INTERACTIVE:
				input = configurarEntrada(fichEntrada, new NullInMethod());
				output = configurarSalida(fichSalida, new NullOutMethod());
				programa = obtenerPrograma(fichPrograma);
				cpu = new CPU(input, output);
				controladorConsola = new ControladorConsola(cpu);
				vistaConsola = new VistaConsolaInteractive(controladorConsola);
				controladorConsola.start(programa);
				break;
			case BATCH:
				input = configurarEntrada(fichEntrada, new StdInMethod());
				output = configurarSalida(fichSalida, new StdOutMethod());
				programa = obtenerPrograma(fichPrograma);
				cpu = new CPU(input, output);
				controladorConsola = new ControladorConsola(cpu);
				vistaConsola = new VistaConsolaBatch(controladorConsola);
				controladorConsola.start(programa);
				break;
			case DEBUG: // por probar que tal funciona, el nombre es arbitrario... xD
				input = configurarEntrada(fichEntrada, new NullInMethod());
				output = configurarSalida(fichSalida, new NullOutMethod());
				programa = obtenerPrograma(fichPrograma);
				cpu = new CPU(input, output);
				controladorSwing = new ControladorSwing(cpu);
				controladorConsola = new ControladorConsola(cpu);
				vistaSwing = new VistaSwing(controladorSwing, input, output);
				vistaConsola = new VistaConsolaInteractive(controladorConsola);
				controladorConsola.start(programa);
				break;
		}
	}


	private static InMethod configurarEntrada(String fichero, InMethod defecto){
		InMethod entradaCpu=null;
		try{
			if(fichero == null)
				entradaCpu = defecto;
			else
				entradaCpu = new FileInMethod(fichero);
		}
		catch(IOException e){
			errorExit(1, "Uso incorrecto: Error al acceder al fichero de entrada (" + fichero + ") \nUse -h|--help para más detalles.");
		}
		return entradaCpu;
	}
	
	
	private static OutMethod configurarSalida(String fichero, OutMethod defecto ){
		OutMethod salidaCpu=null;
		try{
			if(fichero == null)
				salidaCpu = defecto;
			else
				salidaCpu = new FileOutMethod(fichero);
		}
		catch(IOException e){	// supongo que podría pasar...
			errorExit(1, "Uso incorrecto: Error al acceder al fichero de salida (" + fichero + ") \nUse -h|--help para más detalles.");
		}
		return salidaCpu;
	}

	
	private static ProgramMV leerProgramaTeclado(){
		ProgramMV programa = new ProgramMV(100);
		String linea;
		Instruction instruccion;
		boolean terminar = false;
			
		System.out.println("Introduce el programa fuente");
		System.out.print("> ");
		while (!terminar && entrada.hasNextLine()) {
			linea = entrada.nextLine();
			terminar = linea.trim().equalsIgnoreCase("END");
			if (!terminar){
				instruccion = InstructionParser.parse(linea);
				if (instruccion != null)
					programa.agregarInstruccion(instruccion);
				else
					System.out.println("error: instruccion incorrecta");
				System.out.print("> ");
			}
		}
		return programa;
	}
	
	
	private static ProgramMV obtenerPrograma(String fichero){
		ProgramMV programa = null;
		
		if(fichero != null){
			try{
				programa = ASMFileParser.parseFile(fichero);
			}
			catch(FileNotFoundException e){
				errorExit(1, "Error al acceder al fichero ASM ("+ fichero +") \nUse -h|--help para más detalles.");
			}
			catch(ASMCodeParseException | IOException e){
				errorExit(2, e.getMessage());
			}
		}
		else
			programa = leerProgramaTeclado();
		return programa;
	}
	
	private static void mostrarUsage(){
		System.out.println(
				"usage: tp.pr3.mv.Main [-a <asmfile>] [-h] [-i <infile>] [-m <mode>] [-o <outfile>]"
				+ "\n-a,--asm <asmfile> Fichero con el codigo en ASM del programa a ejecutar. Obligatorio en modo batch."
				+ "\n-h,--help Muestra esta ayuda"
				+ "\n-i,--in <infile> Entrada del programa de la maquina-p."
				+ "\n-m,--mode <mode> Modo de funcionamiento (batch | interactive). Por defecto, batch."
				+ "\n-o,--out <outfile> Fichero donde se guarda la salida del programa de la maquina-p.");
	}
	
	private static void errorExit(int codigoError, String mensaje){
		System.err.println(mensaje);
	    entrada.close();
	    System.exit(codigoError);
	}
	
}