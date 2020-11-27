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
            //part of speechの部分は計測ではいらないため，めんどくさいので全部"v"としている．
            {"1","製作","fabrication","n","0"},
            {"2","～を編む","knit","v","0"},
            {"3","引き渡す","extradite","v","0"},
            {"4","～について述べる","mention","v","0"},
            {"5","歓迎","hospitality","n","0"},
            {"6","～をがたがた鳴らす","rattle","v","0"},
            {"7","～に入る","enter","v","0"},
            {"8","配偶者","spouse","n","0"},
            {"9","国","nation","n","0"},
            {"10","代理店","agency","n","0"},
            {"11","陪審員","jury","n","0"},
            {"12","入り江","inlet","n","0"},
            {"13","～に従う","follow","v","0"},
            {"14","ボランティア","volunteer","n","0"},
            {"15","称賛に値する","laudable","adj","0"},
            {"16","ずんぐりした","stocky","adj","0"},
            {"17","～暗唱する","recite","v","0"},
            {"18","～を向上させる","improve","v","0"},
            {"19","～を減らす","reduce","v","0"},
            {"20","冷淡な","callous","adj","0"},
            {"21","華美な装飾品","finery","n","0"},
            {"22","～のままでいる","remain","v","0"},
            {"23","描写","delineation","n","0"},
            {"24","略奪","pillage","v","0"},
            {"25","～に耐える","withstand","v","0"},
            {"26","補助金","subsidy","n","0"},
            {"27","個人","individual","n","0"},
            {"28","きびしい","grim","adj","0"},
            {"29","用心","precaution","n","0"},
            {"30","～を築く","erect","v","0"},
            {"31","～を必要とする","require","v","0"},
            {"32","ひるむ","recoil","v","0"},
            {"33","卒業証書","diploma","n","0"},
            {"34","軽蔑","scorn","n","0"},
            {"35","腐敗する","rot","v","0"},
            {"36","広げる","spread","v","0"},
            {"37","しぼむ","wither","v","0"},
            {"38","～を破壊する","destroy","v","0"},
            {"39","覚醒","arousal","n","0"},
            {"40","渦","whirlpool","n","0"},
            {"41","元気な","lusty","adj","0"},
            {"42","枝編み細工","wicker","n","0"},
            {"43","怒り","indignation","n","0"},
            {"44","消す","efface","v","0"},
            {"45","生み出す","engender","v","0"},
            {"46","～を具現する","embody","v","0"},
            {"47","～を描く","draw","v","0"},
            {"48","しまい込む","stow","v","0"},
            {"49","～を妨げる","hinder","v","0"},
            {"50","病","disease","n","0"},
            {"51","裂け目","crevice","n","0"},
            {"52","外れる","deviate","v","0"},
            {"53","～を嘆く","lament","v","0"},
            {"54","溶剤","solvent","adj","0"},
            {"55","もみ合う","scuffle","v","0"},
            {"56","工業","industry","n","0"},
            {"57","十分の一","tithe","n","0"},
            {"58","共有する","share","v","0"},
            {"59","石油","petroleum","n","0"},
            {"60","～をあざける","mock","v","0"},
            {"61","経済","economy","n","0"},
            {"62","～を雇う","hire","v","0"},
            {"63","～を発展させる","develop","v","0"},
            {"64","～を低下させる","impair","v","0"},
            {"65","勃発","outbreak","n","0"},
            {"66","兆し","sign","n","0"},
            {"67","低迷","doldrums","n","0"},
            {"68","列","queue","n","0"},
            {"69","不機嫌","sulk","n","0"},
            {"70","考える","consider","v","0"},
            {"71","つぶやく","murmur","v","0"},
            {"72","反芻","ruminate","v","0"},
            {"73","使者","emissary","n","0"},
            {"74","勇敢な","valiant","adj","0"},
            {"75","外見","appearance","n","0"},
            {"76","身構える","gird","v","0"},
            {"77","チョッキ","vest","n","0"},
            {"78","時代","period","n","0"},
            {"79","自主性","autonomy","n","0"},
            {"80","～と宣言する","proclaim","v","0"},
            {"81","～を要する","cost","v","0"},
            {"82","特権","prerogative","n","0"},
            {"83","古代","antiquity","n","0"},
            {"84","慣習","custom","n","0"},
            {"85","すね","shin","n","0"},
            {"86","腫瘍","tumor","n","0"},
            {"87","冒涜","blasphemy","n","0"},
            {"88","～を認める","recognize","v","0"},
            {"89","～に達する","reach","v","0"},
            {"90","～を上げる","raise","v","0"},
            {"91","形","shape","n","0"},
            {"92","～を複製する","duplicate","v","0"},
            {"93","地方","region","n","0"},
            {"94","教義","doctrine","n","0"},
            {"95","～を描写する","describe","v","0"},
            {"96","利益","benefit","n","0"},
            {"97","輝く","glitter","v","0"},
            {"98","ウジ","maggot","n","0"},
            {"99","交通","traffic","n","0"},
            {"100","残る","linger","v","0"},
            {"101","～を逃れる","evade","v","0"},
            {"102","存在","entity","n","0"},
            {"103","笑い","laughter","n","0"},
            {"104","国勢調査","census","n","0"},
            {"105","痛み","pain","n","0"},
            {"106","噛む","chew","v","0"},
            {"107","～を誓う","pledge","v","0"},
            {"108","拍手する","applaud","v","0"},
            {"109","砂丘","dune","n","0"},
            {"110","正方形","square","n","0"},
            {"111","～を誤解する","mistake","v","0"},
            {"112","判断する","judge","v","0"},
            {"113","ひそかな","furtive","adj","0"},
            {"114","～を提案する","suggest","v","0"},
            {"115","赤字","deficit","n","0"},
            {"116","勇気","valor","n","0"},
            {"117","印","token","n","0"},
            {"118","価値","value","n","0"},
            {"119","社会","society","n","0"},
            {"120","ピリッとした","peppery","adj","0"},
            {"121","蒸気","vapor","n","0"},
            {"122","～を消す","extinguish","v","0"},
            {"123","俗語","slang","n","0"},
            {"124","～に気づく","notice","v","0"},
            {"125","～を得る","gain","v","0"},
            {"126","堤防","embankment","n","0"},
            {"127","ノード","node","n","0"},
            {"128","獣医","veterinarian","n","0"},
            {"129","突き出す","protrude","v","0"},
            {"130","結果","result","n","0"},
            {"131","～を悟る","realize","v","0"},
            {"132","騎兵","cavalry","n","0"},
            {"133","とぼとぼと歩く","plod","v","0"},
            {"134","法外な","exorbitant","v","0"},
            {"135","～を粉々に砕く","smash","v","0"},
            {"136","委託","consignment","n","0"},
            {"137","落ちる","tumble","v","0"},
            {"138","～と誓う","swear","v","0"},
            {"139","通行料","toll","v","0"},
            {"140","～を反映する","reflect","v","0"},
            {"141","飾り","ornament","n","0"},
            {"142","一般大衆","mass","n","0"},
            {"143","物質","material","n","0"},
            {"144","不活性","inert","adj","0"},
            {"145","戦術","tactics","n","0"},
            {"146","公国","duchy","n","0"},
            {"147","計画","project","n","0"},
            {"148","結婚生活","matrimony","n","0"},
            {"149","ずるそうな","shifty","adj","0"},
            {"150","～誘い込む","lure","v","0"},

            //150以降はdefault用
            {"151","(特に子ども)を叱る","scold","v","0"},
            {"152","～を注ぐ","pour","v","0"},
            {"153","知恵","wisdom","n","0"},
            {"154","(難題、文句など)を扱う","handle","v","0"},
            {"155","(他と比べて)大きい","major","adj","0"},
            {"156","～を混乱させる","confuse","v","0"},
            {"157","～を断る","refuse","v","0"},
            {"158","勇気","courage","n","0"},
            {"159","～勇気づける","encourage","v","0"},
            {"160","～を応援する","cheer","v","0"},
            {"161","場合","occasion","n","0"},
            {"162","さりげない","casual","adj","0"},
            {"163","(数量、価格、程度が)適度の","moderate","adj","0"},
            {"164","責任がある","responsible","adj","0"},
            {"165","～に気が付いている","notice","v","0"},
            {"166","考え","notion","n","0"},
            {"167","順序、秩序","order","n","0"},
            {"168","平凡な","ordinary","adj","0"},
            {"169","(～を)数える、重要である","count","v","0"},
            {"170","邪悪な、悪い","evil","adj","0"},
            {"171","お辞儀する","bow","v","0"},
            {"172","(大量の)荷物","load","n","0"},
            {"173","流儀","manner","n","0"},
            {"174","うなずく","nod","v","0"},
            {"175","食欲","appetite","n","0"},
            {"176","競争する","compete","v","0"},
            {"177","(専門的な技術を備え)有能な","competent","adj","0"},
            {"178","時間厳守の","punctual","adj","0"},
            {"179","～を登録する","register","v","0"},
            {"180","環境","environment","n","0"},
            {"181","～を汚染する","pollute","v","0"},
            {"182","(肉体的な)労働","labor","n","0"},
            {"183","入念な","elaborate","adj","0"},
            {"184","原稿","manuscript","n","0"},
            {"185","表面","surface","n","0"},
            {"186","生き残る、～を乗り越え生き残る","survive","v","0"},
            {"187","～を断る","reject","v","0"},
            {"188","心配な","anxious","adj","0"},
            {"189","客観的な、(政治的)目的","objective","adj","0"},
            {"190","～を囲む","surround","v","0"},
            {"191","宗教","religion","n","0"},
            {"192","(強い)感情","emotion","n","0"},
            {"193","急速に","rapidly","adv","0"},
            {"194","何らかの方法で","somehow","adv","0"},
            {"195","～を苛立たせる","annoy","v","0"},
            {"196","実践的な、現実的な","practical","adj","0"},
            {"197","(新しい方法、装置)を考案する","devise","v","0"},
            {"198","証拠","evidence","n","0"},
            {"199","～をとらえる、認識する","perceive","v","0"},
            {"200","～をだます","deceive","v","0"},
            {"201","鋭く","acutely","adv","0"},
            {"202","はるかに","afar","adv","0"}
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
