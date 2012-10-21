
public class MarkovModel{
	private int OrderK=0;
	private ST<String, ST<String, Integer>> MappedSubstring = new ST<String, ST<String, Integer>>();

	MarkovModel(String text, int k){      // create a Markov model of order k from given tex
	 	String kgram, Preceding;
		try{
			if(k<0) throw new Exception("This is not a valid order!!! ("+k +")");
			OrderK = k;
		}
		catch(Exception exc){ 
			System.out.println(exc.getMessage());
		}
		int j=0;
		for(int i=0; i <= text.length(); i++){
			if(i<text.length()-this.order()){
				kgram = text.substring(i, i+this.order());
				Preceding = text.charAt(i+this.order())+"";
			}
			else{
				
				kgram = text.substring(i, text.length())+text.substring(0,j);	
				Preceding = text.charAt(j++)+"";
			}
			if(MappedSubstring.contains(kgram))
				if(MappedSubstring.get(kgram).contains(Preceding))
					MappedSubstring.get(kgram).put(Preceding, MappedSubstring.get(kgram).get(Preceding) + 1 );
				else	MappedSubstring.get(kgram).put(Preceding, 1 );
			else{
				MappedSubstring.put(kgram, new ST<String, Integer>());
				MappedSubstring.get(kgram).put(Preceding, 1);
			}
		}
	}

	int order(){ 
		return OrderK;                      // order k of Markov model
 	}

   	int freq(String kgram){            // number of occurrences of kgram in text
              	try{
			if(kgram.length()!=OrderK) throw new Exception("The Substring is not of the correct length. ("+kgram.length() +" != "+ OrderK +")");
			if(MappedSubstring.contains(kgram))
				return MappedSubstring.get(kgram).size();	                      // (throw an exception if kgram is not of length k)
			else return 0;
 		}
		catch(Exception exc){
			System.out.println(exc.getMessage());
		}
		return 0;
	}

   	int freq(String kgram, char c){    // number of times that character c follows kgram
             	try{
			if(kgram.length()!=OrderK) throw new Exception("The Substring is not of the correct length. ("+kgram.length() +" != "+ OrderK +")");
			if(MappedSubstring.contains(kgram)){
				if(MappedSubstring.get(kgram).contains(c+""))return MappedSubstring.get(kgram).get(c+"");	                      // (throw an exception if kgram is not of length k)
				else return 0;
			}
			else return 0;
 		}
		catch(Exception exc){
			System.out.println(exc.getMessage());
		}
		return 0;		
	}

  	char rand(String kgram){            // random character following given kgram
		int i = 1;
		if( MappedSubstring.contains(kgram) ){
			int RandomNumber = (int) ( Math.random() * this.freq(kgram) ) + 1; 
			for (String key : MappedSubstring.get(kgram)){
				if( RandomNumber <= i ) return key.charAt(0);
				i = i + this.freq(kgram, key.charAt(0));
			}
		}
		return 'c';
  	}                                   // or if no such kgram)
}
