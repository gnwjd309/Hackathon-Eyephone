package com.example.eyephone_project;

public class setNewsString {

    private static String contents;// = "서울시 선관위가 고 박원순 전 서울시장의 성추행 피해자 A 씨에 대한 고발을 접수, 선거법 위반 여부 검토에 들어갔다고 18일 밝혔다. 선관위 관계자는 이날 통화에서 “A씨가 선거법을 위반했다는 신고가 현재까지 5건가량 있었고 고발 주체는 모두 기관·단체가 아닌 개인”이라고 말했다. A 씨는 전날(17일) 서울 중구 명동의 한 호텔에서 열린 ‘서울시장 위력 성폭력 사건 피해자와 함께 말하기’ 기자회견에 나와 “그분(박 전 시장)의 위력은 여전히 강하게 존재한다”며 “(서울시장 보궐선거를 앞둔) 지금 상황에서 본래 선거가 치러지게 된 계기가 많이 묻혔다고 생각한다”고 밝혔다.“피해 사실을 왜곡하고 상처 줬던 정당에서 시장이 선출됐을 때 저의 자리로 돌아갈 수 없을 것이란 두려움이 든다”는 말도 했다.";
    private static String[] newsContents = new String[1000];
    private static int count=0;

    static void setContent(String value){
        contents = value;

        String[] newsArray = contents.split("\\.");
        String str = newsArray[0];

        for(int k=0; k<newsArray.length; k++){
            int maxLength = 13;
            int textLen = newsArray[k].length();
            int loopCnt = textLen / maxLength + 1;
            String result = "";

            for(int i=0; i<loopCnt; i++){
                int lastIndex = (i+1)*maxLength;

                if(textLen > lastIndex){
                    result = newsArray[k].substring(i*maxLength, lastIndex);
                    newsContents[count] = result;
                    count++;
                }

                else {
                    result = newsArray[k].substring(i*maxLength);
                    newsContents[count] = result;
                    count++;
                }
            }
        }
    }

    static String[] getContent(){
        return newsContents;
    }
    static int getCount() { return count; }
}
