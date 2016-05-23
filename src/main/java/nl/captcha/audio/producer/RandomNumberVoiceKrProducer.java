package nl.captcha.audio.producer;

import java.security.SecureRandom;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import nl.captcha.audio.Sample;
import nl.captcha.util.FileUtil;

public class RandomNumberVoiceKrProducer implements VoiceProducer {

	private static final Random RAND = new SecureRandom();

	private static final Map<Integer, String[]> DEFAULT_VOICES_MAP;

	static {
		DEFAULT_VOICES_MAP = new HashMap<Integer, String[]>();
		String[] files_for_num;
		StringBuilder sb;

		for (int i = 0; i < 10; i++) {
			files_for_num = new String[10];
			for (int j = 0; j < files_for_num.length; j++) {
				sb = new StringBuilder("/sounds/kr/numbers/");
				sb.append("kor-" + i + ".wav");
				files_for_num[j] = sb.toString();
			}
			DEFAULT_VOICES_MAP.put(i, files_for_num);
		}
	}

	private final Map<Integer, String[]> _voices;

	public RandomNumberVoiceKrProducer() {
		this(DEFAULT_VOICES_MAP);
	}

	/**
	 * Creates a <code>RandomNumberVoiceProducer</code> for the given
	 * <code>voices</code>, a map of numbers to their corresponding filenames.
	 * Conceptually the map must look like the following:
	 * 
	 * <pre>
	 * {1 => ["/my_sounds/1-quiet.wav", "/my_sounds/kor-0.wav"],
	 * </pre>
	 * 
	 * @param voices
	 */
	public RandomNumberVoiceKrProducer(Map<Integer, String[]> voices) {
		_voices = voices;
	}

	public Map<Integer, String[]> getVoices() {
		return Collections.unmodifiableMap(_voices);
	}

	@Override
	public final Sample getVocalization(char num) {
		try {
			Integer.parseInt(num + "");
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Expected <num> to be a number, got '" + num + "' instead.", e);
		}

		int idx = Integer.parseInt(num + "");
		String[] files = _voices.get(idx);
		String filename = files[RAND.nextInt(files.length)];
		System.out.println(filename);
		return FileUtil.readSample(filename);
	}
}
