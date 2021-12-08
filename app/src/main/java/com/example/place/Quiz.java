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
            {"1","exhume","掘り出す","v","0"},
            {"2","embarrass","当惑させる","v","0"},
            {"3","implied","暗黙の","v","0"},
            {"4","besides","その上","adv","0"},
            {"5","develop","発達する","v","0"},
            {"6","hydroponic","水耕の","n","0"},
            {"7","engage","従事する","v","0"},
            {"8","doubtful","疑わしい","adj","0"},
            {"9","palaver","無駄話","n","0"},
            {"10","inviolable","不可侵の","adv","0"},
            {"11","gasbag","おしゃべりな人","n","0"},
            {"12","chancy","危険な","n","0"},
            {"13","floorboard","床板","n","0"},
            {"14","neurology","神経学","adv","0"},
            {"15","glance","ちらっと見る","v","0"},
            {"16","cagey","用心深い","adv","0"},
            {"17","enslave","奴隷にする","v","0"},
            {"18","deceive","だます","v","0"},
            {"19","burden","負担","n","0"},
            {"20","particular","ある特定な","adj","0"},
            {"21","seaworthy","航海に適した","adv","0"},
            {"22","influence","に影響を及ぼす","v","0"},
            {"23","fortune","大金","n","0"},
            {"24","disturb","邪魔する","v","0"},
            {"25","warble","さえずり","adv","0"},
            {"26","venerate","あがめる","n","0"},
            {"27","rapidly","急速に","adv","0"},
            {"28","perceive","とらえる","v","0"},
            {"29","lubrication","潤滑","n","0"},
            {"30","unperturbed","動じない","adv","0"},
            {"31","generalissimo","総統","n","0"},
            {"32","hominy","ひき割りトウモロコシ","n","0"},
            {"33","barrister","弁護士","n","0"},
            {"34","liturgy","典礼","n","0"},
            {"35","legation","公使館","n","0"},
            {"36","luxury","贅沢","n","0"},
            {"37","absorb","吸収する","v","0"},
            {"38","audience","聴衆","n","0"},
            {"39","voluntary","自発的な","adj","0"},
            {"40","tendril","巻きひげ","n","0"},
            {"41","competent","有能な","adj","0"},
            {"42","dumpy","みすぼらしい","n","0"},
            {"43","vote","投票する","v","0"},
            {"44","obvious","明白な","adj","0"},
            {"45","admire","高く評価する","v","0"},
            {"46","pursuing","追求","v","0"},
            {"47","leger","元帳","n","0"},
            {"48","chard","フダンソウ","n","0"},
            {"49","upturn","好転","adv","0"},
            {"50","arbitrage","裁定取引","n","0"},
            {"51","budget","予算","n","0"},
            {"52","shoestring","靴ひも","v","0"},
            {"53","polemical","論争の","adv","0"},
            {"54","ideal","理想の","adj","0"},
            {"55","devote","捧げる","v","0"},
            {"56","pram","乳母車","n","0"},
            {"57","crackerjack","極上品","n","0"},
            {"58","reject","断る","v","0"},
            {"59","aspect","見方","n","0"},
            {"60","grasp","つかむ","v","0"},
            {"61","anxious","心配な","adj","0"},
            {"62","disabuse","捨てさせる","n","0"},
            {"63","intermarry","結婚する","adv","0"},
            {"64","doer","やり手","n","0"},
            {"65","afterword","後書き","n","0"},
            {"66","bluish","青みがかった","adv","0"},
            {"67","collarbone","鎖骨","n","0"},
            {"68","objective","客観的な","adj","0"},
            {"69","harm","害","n","0"},
            {"70","erector","建設作業員","n","0"},
            {"71","elaborate","入念な","adj","0"},
            {"72","manner","流儀","n","0"},
            {"73","effect","影響","n","0"},
            {"74","tracker","追跡者","n","0"},
            {"75","literature","文学","n","0"},
            {"76","billionth","10億分の1","n","0"},
            {"77","steeplechase","障害物競走","n","0"},
            {"78","doleful","悲しげな","adv","0"},
            {"79","virulently","敵意に満ちて","adv","0"},
            {"80","peritonitis","腹膜炎","n","0"},
            {"81","erratum","誤字","adv","0"},
            {"82","downspout","縦樋","n","0"},
            {"83","register","登録する","v","0"},
            {"84","determine","を決める","v","0"},
            {"85","disaffected","不満","v","0"},
            {"86","eugenics","優生学","n","0"},
            {"87","affection","愛情","n","0"},
            {"88","whisper","ささやく","v","0"},
            {"89","decade","10年間","n","0"},
            {"90","similar","似ている","adj","0"},
            {"91","imbibe","吸収する","n","0"},
            {"92","panhandler","乞食","n","0"},
            {"93","songbird","歌姫","n","0"},
            {"94","temporary","一時的な","adj","0"},
            {"95","legend","伝説","n","0"},
            {"96","rely","頼る","v","0"},
            {"97","simultaneous","同時の","adj","0"},
            {"98","offend","の気分を害する","v","0"},
            {"99","mature","成熟した","adj","0"},
            {"100","dispirit","がっかりさせる","n","0"},
            {"101","gastronomy","美食","adv","0"},
            {"102","herbivore","草食動物","n","0"},
            {"103","acknowledge","認める","v","0"},
            {"104","porpoise","ネズミイルカ","n","0"},
            {"105","intellectual","知的な","adj","0"},
            {"106","cannery","缶詰工場","n","0"},
            {"107","analysis","分析","n","0"},
            {"108","persevere","頑張り通す","v","0"},
            {"109","pliant","しなやかな","n","0"},
            {"110","gauche","不器用な","n","0"},
            {"111","distribute","分配する","v","0"},
            {"112","trust","信頼する","v","0"},
            {"113","emotion","感情","n","0"},
            {"114","tribe","部族","n","0"},
            {"115","compete","競争する","v","0"},
            {"116","bother","に迷惑をかける","v","0"},
            {"117","resound","鳴り響く","adv","0"},
            {"118","labor","労働","n","0"},
            {"119","battered","ぼろぼろの","v","0"},
            {"120","term","言葉","n","0"},
            {"121","practical","実践的な","adj","0"},
            {"122","aflame","燃えて","n","0"},
            {"123","reflexes","反射神経","n","0"},
            {"124","synthetic","合成の","adj","0"},
            {"125","nod","うなずく","v","0"},
            {"126","misconstrue","誤解","n","0"},
            {"127","petrochemical","石油化学製品","n","0"},
            {"128","wander","さまよう","v","0"},
            {"129","virological","ウイルス学的な","adv","0"},
            {"130","suburb","郊外","n","0"},
            {"131","gouge","えぐる","n","0"},
            {"132","surefire","確実な","n","0"},
            {"133","suspect","ではないかと疑う","v","0"},
            {"134","accompany","に同伴する","v","0"},
            {"135","drawn","描かれた","n","0"},
            {"136","trivial","取るに足らない","adj","0"},
            {"137","lessee","賃借人","n","0"},
            {"138","matchmaker","仲人","n","0"},
            {"139","religion","宗教","n","0"},
            {"140","somehow","何らかの方法で","adv","0"},
            {"141","devise","工夫する","v","0"},
            {"142","relationship","関係","n","0"},
            {"143","volitional","意志の","adv","0"},
            {"144","affect","に影響を及ぼす","v","0"},
            {"145","load","荷物","n","0"},
            {"146","gallows","絞首台","n","0"},
            {"147","object","物体","n","0"},
            {"148","pollute","汚染する","v","0"},
            {"149","fluent","流れるような","adj","0"},
            {"150","chortle","声高に笑う","n","0"},
            {"151","contemporary","同時代の","adj","0"},
            {"152","tissue","組織","n","0"},
            {"153","character","特徴","n","0"},
            {"154","survive","生き残る","v","0"},
            {"155","bristly","剛毛の","adv","0"},
            {"156","quintuplet","五つ子","n","0"},
            {"157","verbal","言葉による","adj","0"},
            {"158","childcare","育児","n","0"},
            {"159","hustings","選挙の手順","n","0"},
            {"160","environment","環境","n","0"},
            {"161","phenomenon","現象","n","0"},
            {"162","discipline","しつけ","n","0"},
            {"163","scriptural","聖書","adv","0"},
            {"164","heliocentric","太陽中心の","adv","0"},
            {"165","surface","表面","n","0"},
            {"166","yuk","不潔なもの","n","0"},
            {"167","evidence","証拠","n","0"},
            {"168","yonder","彼方","n","0"},
            {"169","disputation","論争","n","0"},
            {"170","punctual","時間厳守の","adj","0"},
            {"171","toothache","歯痛","n","0"},
            {"172","characteristic","特徴","n","0"},
            {"173","flaxen","亜麻色の","n","0"},
            {"174","severe","ひどい","adj","0"},
            {"175","ethnography","民族誌","n","0"},
            {"176","headliner","天井","n","0"},
            {"177","transcendence","超越","n","0"},
            {"178","gastronomic","美食の","adv","0"},
            {"179","tranquilizer","精神安定剤","n","0"},
            {"180","splendour","素晴らしさ","n","0"},
            {"181","hardiness","頑健さ","n","0"},
            {"182","stated","述べた","v","0"},
            {"183","spectator","観客","n","0"},
            {"184","appetite","食欲","n","0"},
            {"185","dampness","湿気","n","0"},
            {"186","annoy","苛立たせる","v","0"},
            {"187","vary","様々だ","v","0"},
            {"188","ultimate","最終的な","adj","0"},
            {"189","value","価値","n","0"},
            {"190","contrive","考案する","v","0"},
            {"191","imitate","真似る","v","0"},
            {"192","rueful","悲しそうな","adv","0"},
            {"193","laxative","下剤","adv","0"},
            {"194","surround","囲む","v","0"},
            {"195","unapproved","承認されていない","adv","0"},
            {"196","evanescent","はかない","n","0"},
            {"197","matrimonial","結婚の","adv","0"},
            {"198","insurance","保険","n","0"},
            {"199","manuscript","原稿","n","0"},
            {"200","camellia","ツバキ","n","0"},

            ////
            {"201","faculty","学部","n","0"},
            {"202","span","スパン","n","0"},
            {"203","guilty","有罪","adj","0"},
            {"204","vital","重要","adj","0"},
            {"205","rough","粗い","adj","0"},
            {"206","contemporary","コンテンポラリー","adj","0"},
            {"207","annual","通年","adj","0"},
            {"208","accustomed","慣れた","adj","0"},
            {"209","steady","安定しました","adj","0"},
            {"210","dull","鈍い","adj","0"},
            {"211","keen","鋭い","adj","0"},
            {"212","loose","ゆるい","adj","0"},
            {"213","delicate","繊細","adj","0"},
            {"214","internal","内部","adj","0"},
            {"215","casual","カジュアル","adj","0"},
            {"216","mature","成熟する","adj","0"},
            {"217","concrete","コンクリート","adj","0"},
            {"218","awful","最悪","adj","0"},
            {"219","senior","シニア","adj","0"},
            {"220","overall","全体","adj","0"},
            {"221","tight","きつい","adj","0"},
            {"222","prime","褒美する","adj","0"},
            {"223","genuine","純正","adj","0"},
            {"224","modest","控えめ","adj","0"},
            {"225","minimum","最小","adj","0"},
            {"226","sophisticated","洗練された","adj","0"},
            {"227","latter","後者","adj","0"},
            {"228","bitter","苦い","adj","0"},
            {"229","peculiar","独特の","adj","0"},
            {"230","passive","受動的","adj","0"},
            {"231","ethnic","エスニック","adj","0"},
            {"232","precisely","正確に","adv","0"},
            {"233","meanwhile","その間","adv","0"},
            {"234","lately","近々","adv","0"},
            {"235","barely","かろうじて","adv","0"},
            {"236","scacely","賢く","adv","0"},
            {"237","accordingly","によると","adv","0"},
            {"238","deliberately","故意に","adv","0"},
            {"239","beneath","下","adv","0"},
            {"240","lonely","寂しい","adv","0"},
            {"241","precious","貴重","adv","0"},
            {"242","minor","マイナー","adv","0"},
            {"243","generous","優しい","adv","0"},
            {"244","tropical","トロピカル","adv","0"},
            {"245","vague","漠然","adv","0"},
            {"246","principal","主要な","adv","0"},
            {"247","numerous","多数","adv","0"},
            {"248","rural","田舎","adv","0"},
            {"249","temporary","一時的","adv","0"},
            {"250","artificial","人工的な","adv","0"},
            {"251","visible","見える","adv","0"},

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
            temp[i][7] = quizData[list.get(i)][0];//問題英単語番号
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
