package mainpackage;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;

public class MidiInputReceiver implements Receiver {

	public String name;
	public MidiInputReceiver(String name){
		this.name = name;
	}
	@Override
	public void close() {
		
	}

	@Override
	public void send(MidiMessage msg, long timeStamp) {
		
	}

}
