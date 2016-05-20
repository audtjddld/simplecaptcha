# 한글 숫자 적용 simplecaptcha
> https://github.com/0x0000-dot-ru/simplecaptcha 인용함
> audio captcha 한글용 및 영문으로 사용하기 위해 locale 값을 추가 시킴

\`
		public AudioCaptcha build(String **locale**) {
			// Make sure we have at least one voiceProducer
			if (_voiceProds.size() == 0) {
				addVoice(locale);
			}

			// Convert answer to an array
			char[] ansAry = _answer.toCharArray();

			// Make a List of Samples for each character
			VoiceProducer vProd;
			List<Sample> samples = new ArrayList<Sample>();
			Sample sample;
			for (int i = 0; i < ansAry.length; i++) {
				// Create Sample for this character from one of the
				// VoiceProducers
				vProd = _voiceProds.get(RAND.nextInt(_voiceProds.size()));
				sample = vProd.getVocalization(ansAry[i]);
				samples.add(sample);
			}

			// 3. Add noise, if any, and return the result
			if (_noiseProds.size() > 0) {
				NoiseProducer nProd = _noiseProds.get(RAND.nextInt(_noiseProds.size()));
				_challenge = nProd.addNoise(samples);

				return new AudioCaptcha(this);
			}

			_challenge = Mixer.append(samples);

			return new AudioCaptcha(this);
		}

\`
 
> **RandomNumberVoiceKrProducer.java** 추가 한글 음원 랜덤값 생성기 추가
> **CustomTextProducer.java** captcha로 생성된 메시지로 텍스트 추가
> **CustomNumberVoiceProducer.java** captcha로 생성된 음성 생성 추가

> 음원제공은 : https://github.com/heo-jin-young