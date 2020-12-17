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
            {"1","fabrication","製作","n","0"},
            {"2","knit","～を編む","v","0"},
            {"3","extradite","引き渡す","v","0"},
            {"4","mention","～について述べる","v","0"},
            {"5","hospitality","歓迎","n","0"},
            {"6","rattle","～をがたがた鳴らす","v","0"},
            {"7","enter","～に入る","v","0"},
            {"8","spouse","配偶者","n","0"},
            {"9","nation","国","n","0"},
            {"10","agency","代理店","n","0"},
            {"11","jury","陪審員","n","0"},
            {"12","inlet","入り江","n","0"},
            {"13","follow","～に従う","v","0"},
            {"14","volunteer","ボランティア","n","0"},
            {"15","laudable","称賛に値する","adj","0"},
            {"16","stocky","ずんぐりした","adj","0"},
            {"17","recite","～暗唱する","v","0"},
            {"18","improve","～を向上させる","v","0"},
            {"19","reduce","～を減らす","v","0"},
            {"20","callous","冷淡な","adj","0"},
            {"21","finery","華美な装飾品","n","0"},
            {"22","remain","～のままでいる","v","0"},
            {"23","delineation","描写","n","0"},
            {"24","pillage","略奪","v","0"},
            {"25","withstand","～に耐える","v","0"},
            {"26","subsidy","補助金","n","0"},
            {"27","individual","個人","n","0"},
            {"28","grim","きびしい","adj","0"},
            {"29","precaution","用心","n","0"},
            {"30","erect","～を築く","v","0"},
            {"31","require","～を必要とする","v","0"},
            {"32","recoil","ひるむ","v","0"},
            {"33","diploma","卒業証書","n","0"},
            {"34","scorn","軽蔑","n","0"},
            {"35","rot","腐敗する","v","0"},
            {"36","spread","広げる","v","0"},
            {"37","wither","しぼむ","v","0"},
            {"38","destroy","～を破壊する","v","0"},
            {"39","arousal","覚醒","n","0"},
            {"40","whirlpool","渦","n","0"},
            {"41","lusty","元気な","adj","0"},
            {"42","wicker","枝編み細工","n","0"},
            {"43","indignation","怒り","n","0"},
            {"44","efface","消す","v","0"},
            {"45","engender","生み出す","v","0"},
            {"46","embody","～を具現する","v","0"},
            {"47","draw","～を描く","v","0"},
            {"48","stow","しまい込む","v","0"},
            {"49","hinder","～を妨げる","v","0"},
            {"50","disease","病","n","0"},
            {"51","crevice","裂け目","n","0"},
            {"52","deviate","外れる","v","0"},
            {"53","lament","～を嘆く","v","0"},
            {"54","solvent","溶剤","adj","0"},
            {"55","scuffle","もみ合う","v","0"},
            {"56","industry","工業","n","0"},
            {"57","tithe","十分の一","n","0"},
            {"58","share","共有する","v","0"},
            {"59","petroleum","石油","n","0"},
            {"60","mock","～をあざける","v","0"},
            {"61","economy","経済","n","0"},
            {"62","hire","～を雇う","v","0"},
            {"63","develop","～を発展させる","v","0"},
            {"64","impair","～を低下させる","v","0"},
            {"65","outbreak","勃発","n","0"},
            {"66","sign","兆し","n","0"},
            {"67","doldrums","低迷","n","0"},
            {"68","queue","列","n","0"},
            {"69","sulk","不機嫌","n","0"},
            {"70","consider","考える","v","0"},
            {"71","murmur","つぶやく","v","0"},
            {"72","ruminate","反芻","v","0"},
            {"73","emissary","使者","n","0"},
            {"74","valiant","勇敢な","adj","0"},
            {"75","appearance","外見","n","0"},
            {"76","gird","身構える","v","0"},
            {"77","vest","チョッキ","n","0"},
            {"78","period","時代","n","0"},
            {"79","autonomy","自主性","n","0"},
            {"80","proclaim","～と宣言する","v","0"},
            {"81","cost","～を要する","v","0"},
            {"82","prerogative","特権","n","0"},
            {"83","antiquity","古代","n","0"},
            {"84","custom","慣習","n","0"},
            {"85","shin","すね","n","0"},
            {"86","tumor","腫瘍","n","0"},
            {"87","blasphemy","冒涜","n","0"},
            {"88","recognize","～を認める","v","0"},
            {"89","reach","～に達する","v","0"},
            {"90","raise","～を上げる","v","0"},
            {"91","shape","形","n","0"},
            {"92","duplicate","～を複製する","v","0"},
            {"93","region","地方","n","0"},
            {"94","doctrine","教義","n","0"},
            {"95","describe","～を描写する","v","0"},
            {"96","benefit","利益","n","0"},
            {"97","glitter","輝く","v","0"},
            {"98","maggot","ウジ","n","0"},
            {"99","traffic","交通","n","0"},
            {"100","linger","残る","v","0"},
            {"101","evade","～を逃れる","v","0"},
            {"102","entity","存在","n","0"},
            {"103","laughter","笑い","n","0"},
            {"104","census","国勢調査","n","0"},
            {"105","pain","痛み","n","0"},
            {"106","chew","噛む","v","0"},
            {"107","pledge","～を誓う","v","0"},
            {"108","applaud","拍手する","v","0"},
            {"109","dune","砂丘","n","0"},
            {"110","square","正方形","n","0"},
            {"111","mistake","～を誤解する","v","0"},
            {"112","judge","判断する","v","0"},
            {"113","furtive","ひそかな","adj","0"},
            {"114","suggest","～を提案する","v","0"},
            {"115","deficit","赤字","n","0"},
            {"116","valor","勇気","n","0"},
            {"117","token","印","n","0"},
            {"118","value","価値","n","0"},
            {"119","society","社会","n","0"},
            {"120","peppery","ピリッとした","adj","0"},
            {"121","vapor","蒸気","n","0"},
            {"122","extinguish","～を消す","v","0"},
            {"123","slang","俗語","n","0"},
            {"124","notice","～に気づく","v","0"},
            {"125","gain","～を得る","v","0"},
            {"126","embankment","堤防","n","0"},
            {"127","node","ノード","n","0"},
            {"128","veterinarian","獣医","n","0"},
            {"129","protrude","突き出す","v","0"},
            {"130","result","結果","n","0"},
            {"131","realize","～を悟る","v","0"},
            {"132","cavalry","騎兵","n","0"},
            {"133","plod","とぼとぼと歩く","v","0"},
            {"134","exorbitant","法外な","v","0"},
            {"135","smash","～を粉々に砕く","v","0"},
            {"136","consignment","委託","n","0"},
            {"137","tumble","落ちる","v","0"},
            {"138","swear","～と誓う","v","0"},
            {"139","toll","通行料","v","0"},
            {"140","reflect","～を反映する","v","0"},
            {"141","ornament","飾り","n","0"},
            {"142","mass","一般大衆","n","0"},
            {"143","material","物質","n","0"},
            {"144","inert","不活性","adj","0"},
            {"145","tactics","戦術","n","0"},
            {"146","duchy","公国","n","0"},
            {"147","project","計画","n","0"},
            {"148","matrimony","結婚生活","n","0"},
            {"149","shifty","ずるそうな","adj","0"},
            {"150","lure","～誘い込む","v","0"},

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

    public void set(){
        int Noun_counter = 0;
        int Verb_counter = 0;
        int Adj_counter = 0;
        int Adv_counter = 0;
        int Oth_counter= 0;
        for(int i = 0; i < quizData.length; i++){
            if(quizData[i][3] == "n"){
                Noun[Noun_counter] = quizData[i];
                Noun_counter++;
            }
            else if(quizData[i][3] == "v"){
                Verb[Verb_counter] = quizData[i];
                Verb_counter++;
            }
            else if(quizData[i][3] == "adj"){
                Adjective[Adj_counter] = quizData[i];
                Adj_counter++;
            }
            else if(quizData[i][3] == "adv"){
                Adverb[Adv_counter] = quizData[i];
                Adv_counter++;
            }
            else{
                Other[Oth_counter] = quizData[i];
                Oth_counter++;
            }
        }
    }

    public String[][] GetQuizSet(int sum){
        String temp[][] = new String[sum][8];
        ArrayList<Integer> list = new ArrayList<Integer>();

        // listに値を入れる。この段階では昇順
        for(int i = 0 ; i < 1300 ; i++) {
            list.add(i);
        }
        // シャッフルして、順番を変える
        Collections.shuffle(list);

        for(int i = 0; i < sum ; i++){
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
            temp[i][7] = quizData[list.get(i)][0];//問題英単語番号
        }
        return temp;
    }

    //Quizの出題範囲を限定するための処理
    public String[][] GetQuizSet(int sum, String quizPattern){
        String temp[][] = new String[sum][8];
        ArrayList<Integer> list = new ArrayList<Integer>();


        int Q_Num;
        int Q_endNum;
        switch (quizPattern){
            case "A":
                Q_Num = 0;
                Q_endNum = 30;
                break;

            case "B":
                Q_Num = 30;
                Q_endNum = 60;
                break;

            case "C":
                Q_Num = 60;
                Q_endNum = 90;
                break;

            case "D":
                Q_Num = 90;
                Q_endNum = 120;
                break;

            case "E":
                Q_Num = 120;
                Q_endNum = 150;
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

        for(int i = 0; i < sum ; i++){
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

    public String[][] GetQuizSetEtoJ(int sum, String quizPattern){
        String temp[][] = new String[sum][8];
        ArrayList<Integer> list = new ArrayList<Integer>();


        int Q_Num;
        int Q_endNum;
        switch (quizPattern){
            case "A":
                Q_Num = 0;
                Q_endNum = 30;
                break;

            case "B":
                Q_Num = 30;
                Q_endNum = 60;
                break;

            case "C":
                Q_Num = 60;
                Q_endNum = 90;
                break;

            case "D":
                Q_Num = 90;
                Q_endNum = 120;
                break;

            case "E":
                Q_Num = 120;
                Q_endNum = 150;
                break;

            default:
                Q_Num = quizData.length-50;
                Q_endNum = quizData.length;
        }
        // listに値を入れる。この段階では昇順
        for(; Q_Num < Q_endNum ; Q_Num++) {
            list.add(Q_Num);
        }
        // シャッフルして、順番を変える
        Collections.shuffle(list);

        for(int i = 0; i < sum ; i++){
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
    public String[] GetOtherAnswer(String num){
        String type = quizData[Integer.valueOf(num).intValue() - 1][3];
        Random random = new Random();
        String temp[] = new String[4];
        int keepNum[] = {-1, -1, -1};
        int i = 0;
        switch(type) {
            case "n":
                while(i != 3) {
                    int randomNum = random.nextInt(Noun.length);
                    if(Noun[randomNum][0] != num){
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
                    if(Verb[randomNum][0] != num){
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
                    if(Adjective[randomNum][0] != num){
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
                    if(Adverb[randomNum][0] != num){
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
                if(Other[0][0] == num){
                    temp[0] = Other[1][2]; temp[1] = Adverb[2][2]; temp[2] = Adverb[3][2];
                }
                else if(Other[1][0] == num){
                    temp[0] = Other[0][2]; temp[1] = Adverb[2][2]; temp[2] = Adverb[3][2];
                }
                else if(Other[2][0] == num){
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
    public void setMemory(int num, boolean f){
        if(f == true){
            this.quizData[num][4] = "1";
        }
        else{
            this.quizData[num][4] = "0";
        }
    }


    public String[][] getQuizData() {return quizData;}
    public String[][] getNoun() {return Noun;}
    public String[][] getVerb() {return Verb;}
    public String[][] getAdjective() {return Adjective;}
    public String[][] getAdverb() {return Adverb;}
    public String[][] getOther() {return Other;}
    public String getMemory(int num){return this.quizData[num][4];}
}
