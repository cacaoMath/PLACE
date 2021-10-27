package com.example.place;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

//英単語問題を管理するクラス
public class Quiz {
    protected static final String TAG = Quiz.class.getSimpleName();
    //フィールド
    private String quizData[][] = {
            //{"NUM","EnglishWords","Japanese","Part of speech","Memorize"},
            {"1","hire","雇う","v","0"},
            {"2","pillage","略奪","n","0"},
            {"3","consider","考慮する","v","0"},
            {"4","follow","従う","v","0"},
            {"5","suggest","提案する","v","0"},
            {"6","cavalry","騎兵","v","0"},
            {"7","perceive","知覚する","v","0"},
            {"8","custom","習慣","n","0"},
            {"9","seat","座っている","v","0"},
            {"10","contrary","反対に","adv","0"},
            {"11","scuffle","もみ合い","v","0"},
            {"12","region","地方","n","0"},
            {"13","rabies","狂犬病","n","0"},
            {"14","exorbitant","法外な","adj","0"},
            {"15","concave","凹面","n","0"},
            {"16","salutary","有益な","adj","0"},
            {"17","crevice","裂け目","n","0"},
            {"18","remain","~のままでいる","v","0"},
            {"19","allow","許可する","v","0"},
            {"20","narrow","狭い","adj","0"},
            {"21","laudable","称賛に値する","adj","0"},
            {"22","officer","役人","n","0"},
            {"23","deplorable","嘆かわしい","adj","0"},
            {"24","thus","それゆえ","adv","0"},
            {"25","valor","勇気","n","0"},
            {"26","node","ノード","n","0"},
            {"27","prerogative","特権","n","0"},
            {"28","reach","着く","v","0"},
            {"29","vestige","痕跡","n","0"},
            {"30","plod","とぼとぼと歩く","v","0"},
            {"31","senile","老人性","adj","0"},
            {"32","decide","決意する","v","0"},
            {"33","swine","豚","n","0"},
            {"34","sulk","すねる","v","0"},
            {"35","pain","苦痛","n","0"},
            {"36","injure","傷つける","v","0"},
            {"37","significant","重要な","adj","0"},
            {"38","exist","存在する","v","0"},
            {"39","demand","要求する","v","0"},
            {"40","scrabble","引っかく","v","0"},
            {"41","native","母国の","adj","0"},
            {"42","familiar","よく知られた","adj","0"},
            {"43","experience","経験","n","0"},
            {"44","inert","不活性","adj","0"},
            {"45","breadwinner","稼ぎ手","n","0"},
            {"46","taste","味","n","0"},
            {"47","negative","否定の","adj","0"},
            {"48","realize","悟る","v","0"},
            {"49","inlet","入り江","n","0"},
            {"50","waft","漂う","v","0"},
            {"51","improve","向上させる","v","0"},
            {"52","duchy","公国","n","0"},
            {"53","ruminate","反芻する","v","0"},
            {"54","worry","心配する","v","0"},
            {"55","swindle","詐欺","n","0"},
            {"56","vest","ベスト","n","0"},
            {"57","wicker","枝編み細工","n","0"},
            {"58","favor","好意","n","0"},
            {"59","consignment","委託","n","0"},
            {"60","value","価値","n","0"},
            {"61","trade","貿易","n","0"},
            {"62","kid","子供","n","0"},
            {"63","reduce","減らす","v","0"},
            {"64","engender","生み出す","v","0"},
            {"65","service","公共事業","n","0"},
            {"66","jostle","押し合う","v","0"},
            {"67","complex","複雑な","adj","0"},
            {"68","correct","正しい","adj","0"},
            {"69","peppery","ピリッとした","adj","0"},
            {"70","afoot","進行中の","adv","0"},
            {"71","indemnity","賠償","n","0"},
            {"72","protrude","突き出す","v","0"},
            {"73","range","範囲","n","0"},
            {"74","increase","増える","v","0"},
            {"75","veterinarian","獣医","n","0"},
            {"76","expound","詳しく説明する","v","0"},
            {"77","carousel","回転木馬","n","0"},
            {"78","embankment","堤防","n","0"},
            {"79","result","結果","n","0"},
            {"80","lusty","元気な","adj","0"},
            {"81","recoil","ひるむ","v","0"},
            {"82","develop","発達する","v","0"},
            {"83","expect","予期する","v","0"},
            {"84","alike","似ている","adj","0"},
            {"85","society","社会","n","0"},
            {"86","shift","変える","v","0"},
            {"87","solvent","溶剤","n","0"},
            {"88","matrimony","結婚生活","n","0"},
            {"89","whirlpool","渦","n","0"},
            {"90","thin","薄い","adj","0"},
            {"91","context","文脈","n","0"},
            {"92","miscellaneous","雑多","adj","0"},
            {"93","efface","消す","v","0"},
            {"94","efficient","効率がいい","adj","0"},
            {"95","furtive","ひそかな","adj","0"},
            {"96","stocky","ずんぐりした","adj","0"},
            {"97","gird","身構える","v","0"},
            {"98","quantity","量","n","0"},
            {"99","extradite","引き渡す","v","0"},
            {"100","galley","ガレー船","n","0"},
            {"101","provide","供給する","v","0"},
            {"102","immaterial","重要でない","adj","0"},
            {"103","deviate","外れる","v","0"},
            {"104","suffer","経験する","v","0"},
            {"105","evil","悪い","adj","0"},
            {"106","seldom","めったに…ない","adv","0"},
            {"107","despotic","専制的な","adj","0"},
            {"108","require","必要とする","v","0"},
            {"109","conscious","意識している","adj","0"},
            {"110","shifty","ずるそうな","adj","0"},
            {"111","finery","華美な装飾品","n","0"},
            {"112","tithe","十分の一","n","0"},
            {"113","delineation","描写","n","0"},
            {"114","chemical","化学的な","adj","0"},
            {"115","barrow","手押し車","n","0"},
            {"116","onus","責任","n","0"},
            {"117","continue","続ける","v","0"},
            {"118","valiant","勇敢な","adj","0"},
            {"119","recognize","認める","v","0"},
            {"120","somehow","なぜか","adv","0"},
            {"121","shin","すね","v","0"},
            {"122","share","共有する","v","0"},
            {"123","squeamish","うるさい","adj","0"},
            {"124","fluffy","フワフワした","adj","0"},
            {"125","suppose","~だと思う","v","0"},
            {"126","economy","経済","n","0"},
            {"127","fabrication","製作","n","0"},
            {"128","civil","一般市民の","adj","0"},
            {"129","stow","しまい込む","v","0"},
            {"130","mistake","誤解する","v","0"},
            {"131","callous","冷淡な","adj","0"},
            {"132","blasphemy","冒涜","n","0"},
            {"133","risk","危険","n","0"},
            {"134","aged","年老いた","adj","0"},
            {"135","exhortation","奨励","n","0"},
            {"136","emissary","使者","n","0"},
            {"137","material","物質","n","0"},
            {"138","sign","印","n","0"},
            {"139","former","前の","adj","0"},
            {"140","dune","砂丘","n","0"},
            {"141","solve","解決する","v","0"},
            {"142","nearly","ほとんど","adv","0"},
            {"143","proper","適切な","adj","0"},
            {"144","arousal","覚醒","n","0"},
            {"145","legal","合法の","adj","0"},
            {"146","therefore","それゆえに","adv","0"},
            {"147","equal","平等な","adj","0"},
            {"148","doldrums","低迷","n","0"},
            {"149","vantage","有意","n","0"},
            {"150","maggot","ウジ","n","0"},

            //以降default用
            {"151","scold","(特に子ども)を叱る","v","0"},
            {"152","pour","～を注ぐ","v","0"},
            {"153","wisdom","知恵","n","0"},
            {"154","handle","(難題、文句など)を扱う","v","0"},
            {"155","major","(他と比べて)大きい","adj","0"},
            {"156","confuse","～を混乱させる","v","0"},
            {"157","refuse","～を断る","v","0"},
            {"158","courage","勇気","n","0"},
            {"159","encourage","～勇気づける","v","0"},
            {"160","cheer","～を応援する","v","0"},
            {"161","occasion","場合","n","0"},
            {"162","casual","さりげない","adj","0"},
            {"163","moderate","(数量、価格、程度が)適度の","adj","0"},
            {"164","responsible","責任がある","adj","0"},
            {"165","notice","～に気が付いている","v","0"},
            {"166","notion","考え","n","0"},
            {"167","order","順序、秩序","n","0"},
            {"168","ordinary","平凡な","adj","0"},
            {"169","count","(～を)数える、重要である","v","0"},
            {"170","evil","邪悪な、悪い","adj","0"},
            {"171","bow","お辞儀する","v","0"},
            {"172","load","(大量の)荷物","n","0"},
            {"173","manner","流儀","n","0"},
            {"174","nod","うなずく","v","0"},
            {"175","appetite","食欲","n","0"},
            {"176","compete","競争する","v","0"},
            {"177","competent","(専門的な技術を備え)有能な","adj","0"},
            {"178","punctual","時間厳守の","adj","0"},
            {"179","register","～を登録する","v","0"},
            {"180","environment","環境","n","0"},
            {"181","pollute","～を汚染する","v","0"},
            {"182","labor","(肉体的な)労働","n","0"},
            {"183","elaborate","入念な","adj","0"},
            {"184","manuscript","原稿","n","0"},
            {"185","surface","表面","n","0"},
            {"186","survive","生き残る、～を乗り越え生き残る","v","0"},
            {"187","reject","～を断る","v","0"},
            {"188","anxious","心配な","adj","0"},
            {"189","objective","客観的な、(政治的)目的","adj","0"},
            {"190","surround","～を囲む","v","0"},
            {"191","religion","宗教","n","0"},
            {"192","emotion","(強い)感情","n","0"},
            {"193","rapidly","急速に","adv","0"},
            {"194","somehow","何らかの方法で","adv","0"},
            {"195","annoy","～を苛立たせる","v","0"},
            {"196","practical","実践的な、現実的な","adj","0"},
            {"197","devise","(新しい方法、装置)を考案する","v","0"},
            {"198","evidence","証拠","n","0"},
            {"199","perceive","～をとらえる、認識する","v","0"},
            {"200","deceive","～をだます","v","0"},
            {"201","acutely","鋭く","adv","0"},
            {"202","afar","はるかに","adv","0"},

    };

    private int VerbCount = 0;
    private int NounCount = 0;
    private int AdjectiveCount = 0;
    private int AdverbCount = 0;
    private int OtherCount = 0;
    private String Verb[][];
    private String Noun[][];
    private String Adjective[][];
    private String Adverb[][];
    private String Other[][];

    public Quiz() {
        for(String[] wordArray: quizData){
            switch (wordArray[3]){
                case "v":
                    this.VerbCount++;
                    break;

                case "n":
                    this.NounCount++;
                    break;

                case "adj":
                    this.AdjectiveCount++;
                    break;

                case "adv":
                    this.AdverbCount++;
                    break;

                default:
                    this.OtherCount++;
                    break;

            }
        }
        //各品詞の数をカウントする
        this.Verb  = new String[this.VerbCount][5];
        this.Noun  = new String[this.NounCount][5];
        this.Adjective  = new String[this.AdjectiveCount][5];
        this.Adverb = new String[this.AdverbCount][5];
        this.Other  = new String[this.OtherCount][5];
        this.set();
    }

    private void set(){
        int Noun_counter = 0;
        int Verb_counter = 0;
        int Adj_counter = 0;
        int Adv_counter = 0;
        int Oth_counter= 0;
        for (String[] quizDatum : quizData) {
            switch (quizDatum[3]) {
                case "n":
                    Noun[Noun_counter] = quizDatum;
                    Noun_counter++;
                    break;
                case "v":
                    Verb[Verb_counter] = quizDatum;
                    Verb_counter++;
                    break;
                case "adj":
                    Adjective[Adj_counter] = quizDatum;
                    Adj_counter++;
                    break;
                case "adv":
                    Adverb[Adv_counter] = quizDatum;
                    Adv_counter++;
                    break;
                default:
                    Other[Oth_counter] = quizDatum;
                    Oth_counter++;
                    break;
            }
        }
    }

    //Quizの出題範囲を限定するための処理
    public String[][] GetQuizSet(int setSize, String quizPattern){
        String[][] temp = new String[setSize][8];
        ArrayList<Integer> list = new ArrayList<Integer>();


        int Q_Num;
        int Q_endNum;
        if(setSize > quizData.length/5){
            quizPattern = "default";
        }
        switch (quizPattern){
            case "A":
                Q_Num = 0;
                Q_endNum = setSize;
                break;

            case "B":
                Q_Num = setSize;
                Q_endNum = 2*setSize;
                break;

            case "C":
                Q_Num = 2*setSize;
                Q_endNum = 3*setSize;
                break;

            case "D":
                Q_Num = 3*setSize;
                Q_endNum = 4*setSize;
                break;

            case "E":
                Q_Num = 4*setSize;
                Q_endNum = 5*setSize;
                break;

            default:
                Q_Num = 0;
                Q_endNum = quizData.length;
        }
        // listに値を入れる。この段階では昇順
        for(; Q_Num < Q_endNum ; Q_Num++) {
            list.add(Q_Num);
        }
        // シャッフルして、順番を変える
        Collections.shuffle(list);

        for(int i = 0; i < setSize ; i++){
            String[] ans = GetOtherAnswer(quizData[list.get(i)][0]);
            temp[i][6] = ans[3];//解答
            List<String> answer = Arrays.asList(ans);
            Collections.shuffle(answer);
            temp[i][0] = "Q" + i;//問題の番号
            temp[i][1] = quizData[list.get(i)][1];//問題英単語
            temp[i][2] = answer.get(0);//選択肢1
            temp[i][3] = answer.get(1);//選択肢2
            temp[i][4] = answer.get(2);//選択肢3
            temp[i][5] = answer.get(3);//選択肢4
            temp[i][7] = quizData[list.get(i)][0];//問題英単語
        }
        return temp;
    }


    //問題番号(1～1300)を渡すと他の選択肢と問題番号の解答を返すメソッド
    private String[] GetOtherAnswer(String num){
        String type = quizData[Integer.valueOf(num).intValue() - 1][3];
        Random random = new Random();
        String[] temp = new String[4];
        int[] keepNum = {-1, -1, -1};
        int i = 0;
        switch(type) {
            case "n":
                while(i != 3) {
                    int randomNum = random.nextInt(Noun.length);
                    if(!Noun[randomNum][0].equals(num)){
                        if (randomNum != keepNum[0] && randomNum != keepNum[1]){
                            keepNum[i] = randomNum;
                            i++;
                        }
                    }
                }
                temp[0] = Noun[keepNum[0]][2];
                temp[1] = Noun[keepNum[1]][2];
                temp[2] = Noun[keepNum[2]][2];
                break;

            case "v":
                while(i != 3) {
                    int randomNum = random.nextInt(Verb.length);
                    if(!Verb[randomNum][0].equals(num)){
                        if (randomNum != keepNum[0] && randomNum != keepNum[1]){
                            keepNum[i] = randomNum;
                            i++;
                        }
                    }
                }
                temp[0] = Verb[keepNum[0]][2];
                temp[1] = Verb[keepNum[1]][2];
                temp[2] = Verb[keepNum[2]][2];
                break;

            case "adj":

                while(i != 3) {
                    int randomNum = random.nextInt(Adjective.length);
                    if(!Adjective[randomNum][0].equals(num)){
                        if (randomNum != keepNum[0] && randomNum != keepNum[1]){
                            keepNum[i] = randomNum;
                            i++;
                        }
                    }
                }
                temp[0] = Adjective[keepNum[0]][2];
                temp[1] = Adjective[keepNum[1]][2];
                temp[2] = Adjective[keepNum[2]][2];
                break;

            case "adv":
                while(i != 3) {
                    int randomNum = random.nextInt(Adverb.length);
                    if(!Adverb[randomNum][0].equals(num)){
                        if (randomNum != keepNum[0] && randomNum != keepNum[1]){
                            keepNum[i] = randomNum;
                            i++;
                        }
                    }
                }
                temp[0] = Adverb[keepNum[0]][2];
                temp[1] = Adverb[keepNum[1]][2];
                temp[2] = Adverb[keepNum[2]][2];
                break;
            default:
                if(Other[0][0].equals(num)){
                    temp[0] = Other[1][2]; temp[1] = Adverb[2][2]; temp[2] = Adverb[3][2];
                }
                else if(Other[1][0].equals(num)){
                    temp[0] = Other[0][2]; temp[1] = Adverb[2][2]; temp[2] = Adverb[3][2];
                }
                else if(Other[2][0].equals(num)){
                    temp[0] = Other[0][2]; temp[1] = Adverb[1][2]; temp[2] = Adverb[3][2];
                }
                else{
                    temp[0] = Other[0][2]; temp[1] = Adverb[1][2]; temp[2] = Adverb[2][2];
                }
                break;
        }
        temp[3] = quizData[Integer.valueOf(num).intValue() - 1][2];
        return temp;
    }


    public String[][] getQuizData() {return quizData;}
    public String[][] getNoun() {return Noun;}
    public String[][] getVerb() {return Verb;}
    public String[][] getAdjective() {return Adjective;}
    public String[][] getAdverb() {return Adverb;}
    public String[][] getOther() {return Other;}
    public String getMemory(int num){return this.quizData[num][4];}
}
