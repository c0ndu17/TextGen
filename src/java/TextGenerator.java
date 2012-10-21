public class TextGenerator{

	public static void main(String[] args){
		int Orderk = Integer.parseInt(args[0]);
		int Numberofchars =Integer.parseInt(args[1]);
		String Text = StdIn.readAll();
		MarkovModel Genomic = new MarkovModel(Text, Orderk);
		String Output=null;
		String kgram;
		int Tracker = 0;
		for(int i=0; i < Numberofchars; i++){
			if(i==0) Output = Text.charAt(i) + "";
			else if(i< Orderk){
				Output = Output + Text.charAt(i);
				System.out.println(Output);				
			}
			else{
				kgram = Output.substring( i - Orderk, i );
				if( Genomic.freq(kgram) > 0 ){
					int size = Genomic.freq(kgram);
					Output = Output + Genomic.rand(kgram);
				}	
				else{
					/****************
					*Fill in!!!!!!!!*
					****************/
				}
			}
		}	
		System.out.println(Output);
	}

}
