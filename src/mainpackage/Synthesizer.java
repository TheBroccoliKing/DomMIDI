package mainpackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.sound.midi.*;
import javax.swing.*;


public class Synthesizer{

	static javax.sound.midi.Synthesizer synth;
	static Sequencer sequencer;
	static Receiver reciever; 
	static Transmitter transmitter;
	static MidiDevice device;
	
	public static void MidiHandler(){
		MidiDevice device;
		MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();
		
		for (int i = 0; i < infos.length; i++){
			try{
				device = MidiSystem.getMidiDevice(infos[i]);
				//Does device have transmitters? if so, add to list
				System.out.println(infos[i]);
				
				//get transmitters for each transmitter
				List<Transmitter> transmitters = device.getTransmitters();
				for(int j = 0; j<transmitters.size();j++){
					transmitters.get(j).setReceiver(new MidiInputReceiver(device.getDeviceInfo().toString()));
				}
					Transmitter trans = device.getTransmitter();
					trans.setReceiver(new MidiInputReceiver(device.getDeviceInfo().toString()));
					device.open();
					System.out.println(device.getDeviceInfo()+ " was opened!");
					
					ShortMessage mymsg = new ShortMessage();
					try {
						mymsg.setMessage(ShortMessage.NOTE_ON, 0, 60, 93);
						long timeStamp = -1;
						Receiver rcvr = MidiSystem.getReceiver();
						rcvr.send(mymsg, timeStamp);
						System.out.println("Message Received");
					} catch (InvalidMidiDataException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					} catch(MidiUnavailableException e) {
				System.out.println(e);
			}
		}
		
	}
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		MidiHandler();
		try {
			synth = MidiSystem.getSynthesizer();
			transmitter = MidiSystem.getTransmitter();
			synth.open();
		
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
