package backend;// 珥앷큵 �뙆�씪�쓣 �깮�꽦, �렪吏묓븯怨� �씫�뒗 �옉�뾽�쓣 泥섎━

import java.io.*;

public class SummaryFileManager {
    // 珥앷큵 �뙆�씪�뿉 珥앷큵 �젙蹂대�� ���옣�븯�뒗 硫붿냼�뱶
	private File file = new File("summary.txt");
	public SummaryFileManager() {
		try {
			if (file.createNewFile()) {
				try (PrintWriter writer = new PrintWriter(new FileWriter("summary.txt"))) {
		            for (int i = 0; i < 9; i++) {
		                writer.println("0.0,0.0"); // �븳 以꾩뵫 珥앷큵 �젙蹂대�� �뙆�씪�뿉 ���옣
		            }
		        } catch (IOException e) {
		            e.printStackTrace(); // �뙆�씪 ���옣 以� 諛쒖깮�븯�뒗 �삁�쇅 異쒕젰
		        }
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    public static void saveSummaryToFile(String[][] summaryData) { //
    	
    	try (PrintWriter writer = new PrintWriter(new FileWriter("summary.txt"))) {
            for (int i = 0; i < 9; i++) {
                String line = summaryData[i][0] + summaryData[i][1];
                writer.println(line); // �븳 以꾩뵫 珥앷큵 �젙蹂대�� �뙆�씪�뿉 ���옣
            }
        } catch (IOException e) {
            e.printStackTrace(); // �뙆�씪 ���옣 以� 諛쒖깮�븯�뒗 �삁�쇅 異쒕젰
        }
    }

    // �뙆�씪�뿉�꽌 珥앷큵 �젙蹂대�� �씫�뼱�� �뒪�듃留� 諛곗뿴濡� 諛섑솚�븯�뒗 硫붿냼�뱶
    public static String[][] readSummaryFromFile(String raw_data) {
        String [][] summaryData = new String[9][2];
        try (BufferedReader reader = new BufferedReader(new FileReader(raw_data))) {
            String line;
            int i = 0;
            while ((line = reader.readLine()) != null) {
                String[] summaryLine = line.split(","); // �븳 以꾩쓣 �돹�몴濡� �굹�닠 諛곗뿴�뿉 ���옣
                for (int j = 0; j < 2; j++) {
                	summaryData[j][i] = summaryLine[j]; // 珥앷큵 �젙蹂� List�뿉 異붽�
                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace(); // �뙆�씪 �씫湲� 以� 諛쒖깮�븯�뒗 �삁�쇅 異쒕젰
        }
        return summaryData;
    }
}
