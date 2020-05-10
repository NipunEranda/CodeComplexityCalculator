package Service.inheritance_complexity;

public class CalculateInheritanceComplexity {

	public static int CalculateCi(String[] stArray) {
		
		for(int y=0;y<stArray.length;y++) {
			if(stArray[y].contains("class")) {
				
				//direct inheritance - java
				if(stArray[y].contains("extends")  ) {
					return 1;
				}
				//indirect inheritance - java
				else if(stArray[y].contains("toString")){
					return 1;
				}
				//inheritance - cpp
				else if(stArray[y].contains(": public") || stArray[y].contains(":public") ) {
					return 1;
				}
				else if(stArray[y].contains(" : public") || stArray[y].contains(" :public") ) {
					return 1;
				}
			}
		}
		
		return 0;
	}
}
