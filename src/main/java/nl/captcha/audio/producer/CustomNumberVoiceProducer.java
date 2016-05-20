package nl.captcha.audio.producer;

import java.util.HashMap;
import java.util.Map;

import nl.captcha.audio.Sample;
import nl.captcha.util.FileUtil;

public class CustomNumberVoiceProducer implements VoiceProducer {

	private Map<Integer, String[]> _voices;

	public CustomNumberVoiceProducer(char[] answer, String locale) throws Exception {

		if (!locale.equals("en") && !locale.equals("kr")) {
			throw new Exception("locale : " + locale);
		}

		this._voices = new HashMap<Integer, String[]>();

		String[] files_for_num;
		StringBuilder sb;
		String soundSrc = "/sounds/" + locale + "/numbers/";
		for (int i = 0; i < 10; i++) {
			files_for_num = new String[10];
			for (int j = 0; j < files_for_num.length; j++) {
				sb = new StringBuilder(soundSrc);
				sb.append("kor");
				sb.append("-");
				sb.append(j);
				sb.append(".wav");
				files_for_num[j] = sb.toString();
			}
			_voices.put(i, files_for_num);
		}
	}

	@Override
	public Sample getVocalization(char num) {
		try {
			Integer.parseInt(num + "");
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Expected <num> to be a number, got '" + num + "' instead.", e);
		}

		int idx = Integer.parseInt(num + "");
		String[] files = _voices.get(idx);
		System.out.println("idx " + idx);
		String filename = files[idx];

		return FileUtil.readSample(filename);
	}

}
