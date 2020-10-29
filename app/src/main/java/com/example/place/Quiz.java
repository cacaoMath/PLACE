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
            {"1","linger","残る","v","0"},
            {"2","petroleum","石油","n","0"},
            {"3","spouse","配偶者","n","0"},
            {"4","mock","～をあざける","v","0"},
            {"5","glitter","輝く","v","0"},
            {"6","withstand","～に耐える","v","0"},
            {"7","knit","～を編む","v","0"},
            {"8","chew","噛む","v","0"},
            {"9","scorn","軽蔑","n","0"},
            {"10","queue","列","n","0"},
            {"11","duplicate","～を複製する","v","0"},
            {"12","tumor","腫瘍","n","0"},
            {"13","applaud","～拍手する","v","0"},
            {"14","outbreak","勃発","n","0"},
            {"15","doctrine","教義","n","0"},
            {"16","precaution","用心","n","0"},
            {"17","census","国勢調査","n","0"},
            {"18","jury","陪審員","n","0"},
            {"19","swear","～と誓う","v","0"},
            {"20","extinguish","～を消す","v","0"},
            {"21","smash","~を粉々に砕く","v","0"},
            {"22","diploma","卒業証書","n","0"},
            {"23","vapor","蒸気","n","0"},
            {"24","toll","通行料","n","0"},
            {"25","lure","～誘い込む","v","0"},
            {"26","lament","~を嘆く","v","0"},
            {"27","slang","俗語","n","0"},
            {"28","wither","しぼむ","v","0"},
            {"29","tactics","戦術","n","0"},
            {"30","embody","～を具現する","v","0"},
            {"31","antiquity","古代","n","0"},
            {"32","token","印","n","0"},
            {"33","impair","～を低下させる","v","0"},
            {"34","recite","～暗唱する","v","0"},
            {"35","hinder","～を妨げる","v","0"},
            {"36","subsidy","補助金","n","0"},
            {"37","rattle","～をがたがた鳴らす","v","0"},
            {"38","hospitality","歓迎","n","0"},
            {"39","proclaim","~と宣言する","v","0"},
            {"40","evade","～を逃れる","v","0"},
            {"41","tumble","落ちる","v","0"},
            {"42","autonomy","自主性","n","0"},
            {"43","indignation","怒り","n","0"},
            {"44","erect","～を築く","v","0"},
            {"45","pledge","～を誓う","v","0"},
            {"46","murmur","つぶやく","v","0"},
            {"47","ornaments","飾り","n","0"},
            {"48","entity","存在","n","0"},
            {"49","deficit","赤字","n","0"},
            {"50","rot","腐敗する","v","0"},
            {"51","persecution","迫害","n","0"},
            {"52","parasite","寄生生物","n","0"},
            {"53","masterpiece","傑作","n","0"},
            {"54","stroll","ぶらつく","v","0"},
            {"55","shatter","~を粉々にする","v","0"},
            {"56","chill","寒気","n","0"},
            {"57","coward","臆病者","n","0"},
            {"58","whip","~をむち打つ","v","0"},
            {"59","soothe","～をなだめる","v","0"},
            {"60","contemplate","～を考える","v","0"},
            {"61","mortality","死亡","n","0"},
            {"62","deteriorate","悪化する","v","0"},
            {"63","burglar","強盗","n","0"},
            {"64","revenue","収入","n","0"},
            {"65","melancholy","憂鬱","n","0"},
            {"66","degrade","～を悪化させる","v","0"},
            {"67","menace","脅威","n","0"},
            {"68","province","州","n","0"},
            {"69","apparatus","装置","n","0"},
            {"70","cuisine","料理","n","0"},
            {"71","propaganda","宣伝","n","0"},
            {"72","feat","偉業","n","0"},
            {"73","ponder","～を熟考する","v","0"},
            {"74","wrinkle","しわ","n","0"},
            {"75","cosmos","宇宙","n","0"},
            {"76","sue","～を訴える","v","0"},
            {"77","transaction","取引","n","0"},
            {"78","affirm","～と断言する","v","0"},
            {"79","breeze","そよ風","n","0"},
            {"80","lump","こぶ","n","0"},
            {"81","germ","細菌","n","0"},
            {"82","undone","元に戻らない","v","0"},
            {"83","adore","～を崇拝する","v","0"},
            {"84","summon","~を呼ぶ","v","0"},
            {"85","reap","～を手に入れる","v","0"},
            {"86","shrug","～をすくめる","v","0"},
            {"87","irrigation","かんがい","n","0"},
            {"88","slap","~をぴしゃりと打つ","v","0"},
            {"89","insert","～を差し込む","v","0"},
            {"90","deforestation","森林破壊","n","0"},
            {"91","slaughter","～を虐殺する","v","0"},
            {"92","preach","説教する","v","0"},
            {"93","compile","～をまとめる","v","0"},
            {"94","stake","賭け金","n","0"},
            {"95","defy","～に逆らう","v","0"},
            {"96","rhetoric","美辞麗句","n","0"},
            {"97","retrieve","～を検索する","v","0"},
            {"98","riot","暴動","n","0"},
            {"99","pierce","～に穴をあける","v","0"},
            {"100","specimen","標本","n","0"},
            {"101","vow","～を誓う","v","0"},
            {"102","rejoice","喜ぶ","v","0"},
            {"103","undermine","～を弱める","v","0"},
            {"104","temperament","気質","n","0"},
            {"105","clarify","～を明らかにする","v","0"},
            {"106","certificate","証明書","n","0"},
            {"107","blur","～をぼやかす","v","0"},
            {"108","peril","危機","n","0"},
            {"109","barbarian","野蛮人","n","0"},
            {"110","disguise","～を隠す","v","0"},
            {"111","spur","～を駆り立てる","v","0"},
            {"112","plight","苦境","n","0"},
            {"113","prose","散文","n","0"},
            {"114","curb","～を抑制する","v","0"},
            {"115","monarch","君主","n","0"},
            {"116","haunt","～付きまとわれる","v","0"},
            {"117","reckon","～と考える","v","0"},
            {"118","mourn","~を悲しむ","v","0"},
            {"119","stun","びっくりさせる","v","0"},
            {"120","choke","喉が詰まる","v","0"},
            {"121","prophet","預言者","n","0"},
            {"122","apprehension","不安","n","0"},
            {"123","coverage","報道","n","0"},
            {"124","supervisor","監督者","n","0"},
            {"125","novelty","目新しさ","n","0"},
            {"126","catastrophe","大災害","n","0"},
            {"127","probe","探査機","n","0"},
            {"128","roam","～を歩き回る","v","0"},
            {"129","foresee","～を予知する","v","0"},
            {"130","perplex","～困惑させる","v","0"},
            {"131","aisle","通路","n","0"},
            {"132","outlet","はけ口","n","0"},
            {"133","aristocracy","貴族階級","n","0"},
            {"134","discharge","～を放出する","v","0"},
            {"135","predecessor","前任","n","0"},
            {"136","stalk","～に忍び寄る","v","0"},
            {"137","tyranny","圧制","n","0"},
            {"138","testify","～証言する","v","0"},
            {"139","stumble","つまづく","v","0"},
            {"140","console","～をなぐさめる","v","0"},
            {"141","flatter","～にお世辞を言う","v","0"},
            {"142","narrative","話","n","0"},
            {"143","intuition","直感","n","0"},
            {"144","legacy","遺産","n","0"},
            {"145","deficiency","欠乏","n","0"},
            {"146","pavement","歩道","n","0"},
            {"147","evoke","～を呼び起こす","v","0"},
            {"148","suck","～を吸う","v","0"},
            {"149","poke","～を突く","v","0"},
            {"150","cradle","ゆりかご","n","0"},
            {"151","discern","～を識別する","v","0"},
    };

    //手動でString[?][5]の?を入れる
    private String Verb[][] = new String[75][5];
    private String Noun[][] = new String[76][5];
    private String Adjective[][] = new String[257][5];
    private String Adverb[][] = new String[22][5];
    private String Other[][] = new String[4][5];

    public Quiz() {
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
            temp[i][7] = quizData[list.get(i)][0];//問題英単語
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
