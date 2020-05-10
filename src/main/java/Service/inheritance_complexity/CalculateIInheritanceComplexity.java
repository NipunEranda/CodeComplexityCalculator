package Service.inheritance_complexity;

public class CalculateIInheritanceComplexity {

	public static int CalculateCii(String[] stArray) {
		
		for(int y=0;y<stArray.length;y++) {
			if(stArray[y].contains("class")) {
				
				
				//indirect inheritance - java
				 if(stArray[y].contains("toString")){
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
