public class AbilityFactory{
	
	public static enum Ability{
		bolaDeSabiduria, induccion, h1, h2, h3;
	}

	public static Ability createAbility(Ability a){
		switch(a){
			case bolaDeSabiduria:
				return new BolaDeSabiduria();
			case induccion:
				return new Induccion();			
			case h1:

			case h2:

			case h3:
		}
	}

	//Cambiar valores
	private class BolaDeSabiduria extends Abilities{

		public BolaDeSabiduria(){
			super("Bola de Sabiduria", 10, 10, 0);
		}

	}

	private class Induccion extends Abilities{
		public class Induccion(){
			super("Induccion", 10,10,0);
		}
	}



}