package nl.captcha.text.producer;

public class CustomTextProducer implements TextProducer {

	private char[] _srcChars;
	private int _length;
	
	@Override
	public String getText() {
		String capText = "";
        for (int i = 0; i < _length; i++) {
            capText += _srcChars[i];
        }
        return capText;
	}
	
	public CustomTextProducer(char[] strChars) {
		this._srcChars = strChars;
		this._length = strChars.length;
	}
}
