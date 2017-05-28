package mainpackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.sound.midi.*;
import javax.sound.midi.MidiDevice.Info;
import javax.swing.*;

import com.sun.xml.internal.bind.unmarshaller.InfosetScanner;

public class Synthesizer{

	static javax.sound.midi.Synthesizer synth;
	static Sequencer sequencer;
	static Receiver reciever; 
	static Transmitter transmitter;
	static Vector<Info> synthInfos;
	static MidiDevice device;
	static MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		try {
			synth = MidiSystem.getSynthesizer();
			transmitter = MidiSystem.getTransmitter();
			synth.open();

			for(int i = 0; i < infos.length; i++){
				try{
					device = MidiSystem.getMidiDevice(infos[i]);
				} catch (MidiUnavailableException e){
					// Handle or Throw Exception
				} if (device instanceof Synthesizer){
					synthInfos.add(infos[i]);
				}
				System.out.println(synthInfos);
			}
		
			final MidiChannel[] mc = synth.getChannels();
			Instrument[] instr = synth.getDefaultSoundbank().getInstruments();
			synth.loadInstrument(instr[90]);
			
			JFrame frame = new JFrame("Sound1");
			JPanel pane = new JPanel();
			JButton button1 = new JButton("Click me!");
			frame.getContentPane().add(pane);
			pane.add(button1);
			frame.pack();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.show();
					
			button1.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					mc[5].noteOn(60, 600);
				}
			});
			
			
			
		} catch (MidiUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}

}
