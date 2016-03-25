package ehack.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class JsonUtil {
	/**
	 * ������ ����� JSON ���� �԰ݿ� �°� ������ִ� �Լ�
	 * @param mapSource ��� ������
	 * @return JSON�԰ݿ� ���� ������� ��� ������
	 */
	public static Map<String, Object> putSuccessJsonContainer(Map<String, Object> mapSource) {
		return makeJsonContainer("success", mapSource);
	}
	
	/**
	 * ����� ������ ���
	 * @param errorCd �����ڵ�(Ŭ������+EER+����)
	 * @param errorMsg �����޽���(����ڰԿ��� ������ �޽���)
	 * @param currentTime ���� �޽��� �߻� �ð�
	 * @return Map������ ���� ���
	 */
	public static Map<String, Object> putFailJsonContainer(String errorCd, int errorCode, String errorMsg, long currentTime) {
		Map<String, Object> mapResData = new HashMap<String, Object>();
		mapResData.put("errorCd", errorCd);
		mapResData.put("errorCode", errorCode);
		mapResData.put("errorMsg", errorMsg);
		mapResData.put("time", currentTime);
		
		return makeJsonContainer("error", mapResData);
	}
	
	/**
	 * �׼��ڵ带 ���� ���� �Լ� (actcd�� 9999�⺻)
	 * @param errorCd �����ڵ� 
	 * @param errorMsg �����޽���
	 * @return
	 */
	public static Map<String, Object> putFailJsonContainer(String errorCd, String errorMsg){
		return putFailJsonContainer(errorCd,9999, errorMsg, System.currentTimeMillis());
	}
	
	/**
	 * JSON ǥ�� �����̳ʿ� �־��ش�. 
	 * @param result ��� �޽���
	 * @param mapSource ���ҽ�
	 * @return ������� Map����
	 */
	private static Map<String, Object> makeJsonContainer(String result, Map<String, Object> mapSource) {
		@SuppressWarnings("rawtypes")
		ArrayList<Map> arrayRsltData = new ArrayList<Map>();
		Map<String, Object> mapResData = new HashMap<String, Object>();
		arrayRsltData.add(mapSource);
		mapResData.put("result", result);
		mapResData.put("resData", arrayRsltData);
		return mapResData;
	}

	

}