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
            {"1","brevity","簡潔","n","0"},
            {"2","mite","m","n","0"},
            {"3","impertinent","imper imper","adj","0"},
            {"4","consignment","委託","n","0"},
            {"5","waft","漂わせる","v","0"},
            {"6","insecticide","虫眼鏡","n","0"},
            {"7","upholstery","室内装飾品","n","0"},
            {"8","predisposition","pred pred","n","0"},
            {"9","javelin","ジャベリン","n","0"},
            {"10","cryptic","潜在的な","adj","0"},
            {"11","opinionated","opinion opinion","adj","0"},
            {"12","plumb","pl pl","n","0"},
            {"13","zest","熱意","n","0"},
            {"14","induction","誘導","n","0"},
            {"15","ancillary","anc anc","adj","0"},
            {"16","loiter","lo lo","v","0"},
            {"17","inscrutable","ins ins","adj","0"},
            {"18","recrimination","recr recr","n","0"},
            {"19","acreage","めちゃくちゃ","n","0"},
            {"20","forgery","偽造","n","0"},
            {"21","allay","和らげる","v","0"},
            {"22","ovary","卵巣","n","0"},
            {"23","glucose","グルコース","n","0"},
            {"24","pounce","急上昇","v","0"},
            {"25","diction","辞書","n","0"},
            {"26","smolder","スムーダー","v","0"},
            {"27","wicker","小枝細工","n","0"},
            {"28","judicious","賢明な","adj","0"},
            {"29","ruminate","轟々","v","0"},
            {"30","corollary","腐敗した","n","0"},
            {"31","galley","gal","n","0"},
            {"32","slacken","スラッケン","v","0"},
            {"33","onlooker","Onlooker","n","0"},
            {"34","puberty","思春期","n","0"},
            {"35","fluffy","ふわふわ","adj","0"},
            {"36","forage","飼料","n","0"},
            {"37","esoteric","難病","adj","0"},
            {"38","muck","泥","n","0"},
            {"39","crochet","かぎ針編み","n","0"},
            {"40","redress","救う","v","0"},
            {"41","dribble","ドリブルする","v","0"},
            {"42","fabrication","生成","n","0"},
            {"43","capricious","気まぐれな","adj","0"},
            {"44","impersonate","imp imp","v","0"},
            {"45","insulation","絶縁","n","0"},
            {"46","kindred","kindred","n","0"},
            {"47","voluble","太った","n","0"},
            {"48","balm","香ばしい","n","0"},
            {"49","primordial","原始の","adj","0"},
            {"50","cavalry","騎兵","n","0"},
            {"51","flail","羽毛","n","0"},
            {"52","cataclysm","カタシュズム","n","0"},
            {"53","detract","損なわれる","v","0"},
            {"54","demoralize","悪魔","v","0"},
            {"55","topography","トポグラフィー","n","0"},
            {"56","ostentatious","ost.","adj","0"},
            {"57","valor","勇気","n","0"},
            {"58","nitrate","硝酸塩","n","0"},
            {"59","extrovert","外向的な","n","0"},
            {"60","repudiate","否認する","v","0"},
            {"61","agrarian","農地","adj","0"},
            {"62","decentralize","分散化する","v","0"},
            {"63","outlay","費用","n","0"},
            {"64","solstice","Solstice.","n","0"},
            {"65","dissect","diss diss","v","0"},
            {"66","assessor","賞賛","n","0"},
            {"67","abdicate","退院する","v","0"},
            {"68","profusion","prof prof","n","0"},
            {"69","wand","w","n","0"},
            {"70","scruffy","みすぼらしい","adj","0"},
            {"71","gentile","gent gent","n","0"},
            {"72","indolent","ind ind","adj","0"},
            {"73","propagate","急降下する","v","0"},
            {"74","stead","st","n","0"},
            {"75","bequeath","be","v","0"},
            {"76","tempestuous","恒久","adj","0"},
            {"77","dingy","すすけた","adj","0"},
            {"78","thesaurus","シソーラス","n","0"},
            {"79","lisp","舌足らずの発音","v","0"},
            {"80","untenable","un un","adj","0"},
            {"81","peevish","p p","adj","0"},
            {"82","salient","臆病な","adj","0"},
            {"83","gratuitous","無料の","adj","0"},
            {"84","hoist","ホイスト","v","0"},
            {"85","facetious","ふざけた","adj","0"},
            {"86","haphazard","行き当たりばったり","n","0"},
            {"87","homogeneous","同種の","adj","0"},
            {"88","genus","属","n","0"},
            {"89","detest","忌み嫌う","v","0"},
            {"90","hedgehog","ハリネズミー","n","0"},
            {"91","stupendous","stup stup","adj","0"},
            {"92","oxide","酸化物","n","0"},
            {"93","subsistence","subs子","n","0"},
            {"94","virile","virile","adj","0"},
            {"95","ellipse","楕円","n","0"},
            {"96","douse","ゆがんで","v","0"},
            {"97","matron","マトリン","n","0"},
            {"98","dissertation","論文","n","0"},
            {"99","vector","ベクター","n","0"},
            {"100","postulate","公準","v","0"},
            {"101","nettle","イラクター","n","0"},
            {"102","supersede","取り替える","v","0"},
            {"103","garish","ぎこちない","adj","0"},
            {"104","pique","立腹","n","0"},
            {"105","contravene","抑圧者","v","0"},
            {"106","abhorrent","ab ab","adj","0"},
            {"107","sundry","sun sun","n","0"},
            {"108","rebuttal","反論","n","0"},
            {"109","reimburse","絶対に","v","0"},
            {"110","ineligible","不適格","adj","0"},
            {"111","rapt","rap rap","adj","0"},
            {"112","zenith","天頂","n","0"},
            {"113","peal","p p","v","0"},
            {"114","tenancy","ten ten","n","0"},
            {"115","hermit","隠者","n","0"},
            {"116","whitewash","ホワイトウォッシュ","n","0"},
            {"117","leaden","リュードレン","adj","0"},
            {"118","angler","釣り人","n","0"},
            {"119","shoal","浅瀬","n","0"},
            {"120","airtight","気密","n","0"},
            {"121","preponderance","der prep","n","0"},
            {"122","embroidery","刺繍","n","0"},
            {"123","scum","スカム","v","0"},
            {"124","recuperate","復活する","v","0"},
            {"125","susceptibility","感受性","n","0"},
            {"126","onus","責任","n","0"},
            {"127","dally","dally","v","0"},
            {"128","hedonism","ヘドニズム","n","0"},
            {"129","accession","即位","n","0"},
            {"130","antagonize","拮抗","v","0"},
            {"131","vulture","v v","n","0"},
            {"132","fluctuate","変動する","v","0"},
            {"133","scrabble","scr scr","v","0"},
            {"134","condescend","cond cond","v","0"},
            {"135","aggregation","集計","n","0"},
            {"136","daft","ダフト","adj","0"},
            {"137","affront","侮辱","v","0"},
            {"138","solvent","溶媒","n","0"},
            {"139","embryonic","胚の","n","0"},
            {"140","stallion","牡馬","n","0"},
            {"141","forfeit","f","n","0"},
            {"142","armistice","休戦","n","0"},
            {"143","jovial","陽気な","adj","0"},
            {"144","detrimental","有害な","adj","0"},
            {"145","incredulous","信頼できる","adj","0"},
            {"146","synopsis","概要","n","0"},
            {"147","alderman","アーダン","n","0"},
            {"148","pike","パイク","n","0"},
            {"149","transitory","trans trans","adj","0"},
            {"150","interlock","インターロック","v","0"},
            {"151","proceed","進む","v","0"},
            {"152","ensure","確実にする","v","0"},
            {"153","interpret","解釈する","v","0"},
            {"154","cease","を止める","v","0"},
            {"155","spoil","台無しにする","v","0"},
            {"156","obey","従う","v","0"},
            {"157","eliminate","除去する","v","0"},
            {"158","resist","抵抗する","v","0"},
            {"159","accompany","同伴する","v","0"},
            {"160","weigh","重さがある","v","0"},
            {"161","pursue","追及する","v","0"},
            {"162","demonstrate","明らかに示す","v","0"},
            {"163","amuse","楽しませる","v","0"},
            {"164","ruin","台無しにする","v","0"},
            {"165","regret","後悔する","v","0"},
            {"166","attach","くっつける","v","0"},
            {"167","reverse","反対にする","v","0"},
            {"168","restrict","制限する","v","0"},
            {"169","compose","組み立てる","v","0"},
            {"170","capture","捕らえる","v","0"},
            {"171","substitute","代わりに用いる","v","0"},
            {"172","trace","跡をたどる","v","0"},
            {"173","interrupt","妨げる","v","0"},
            {"174","confront","立ち向かう","v","0"},
            {"175","illustrate","示す","v","0"},
            {"176","arrest","逮捕する","v","0"},
            {"177","stimulate","刺激する","v","0"},
            {"178","assure","保証する","v","0"},
            {"179","consult","相談する","v","0"},
            {"180","depress","憂鬱にさせる","v","0"},
            {"181","crash","激突する","v","0"},
            {"182","inspire","奮起させる","v","0"},
            {"183","specialize","専攻する","v","0"},
            {"184","cultivate","育む","v","0"},
            {"185","fulfill","果たす","v","0"},
            {"186","transmit","伝える","v","0"},
            {"187","found","設立する","v","0"},
            {"188","cheer","励ます","v","0"},
            {"189","burst","破裂する","v","0"},
            {"190","bow","お辞儀する","v","0"},
            {"191","dismiss","無視する","v","0"},
            {"192","breed","繁殖する","v","0"},
            {"193","prohibit","禁じる","v","0"},
            {"194","oblige","強いる","v","0"},
            {"195","qualify","適任である","v","0"},
            {"196","invest","投資する","v","0"},
            {"197","grasp","理解する","v","0"},
            {"198","collapse","崩壊する","v","0"},
            {"199","overlook","見落とす","v","0"},
            {"200","accuse","非難する","v","0"},
            {"201","proportion","比率","n","0"},
            {"202","contract","契約","n","0"},
            {"203","appointment","予約","n","0"},
            {"204","treasure","財宝","n","0"},
            {"205","stock","株","n","0"},
            {"206","facility","設備","n","0"},
            {"207","sum","金額","n","0"},
            {"208","rank","地位","n","0"},
            {"209","democracy","民主主義","n","0"},
            {"210","emergency","緊急事態","n","0"},
            {"211","protest","抗議","n","0"},
            {"212","track","小道","n","0"},
            {"213","vehicle","車","n","0"},
            {"214","stuff","物","n","0"},
            {"215","row","列","n","0"},
            {"216","wheel","車輪","n","0"},
            {"217","dawn","夜明け","n","0"},
            {"218","welfare","福祉","n","0"},
            {"219","perspective","見方","n","0"},
            {"220","enthusiasm","熱意","n","0"},
            {"221","faith","信頼","n","0"},
            {"222","occupation","職業","n","0"},
            {"223","witness","証人","n","0"},
            {"224","kingdom","王国","n","0"},
            {"225","equivalent","同等のもの","n","0"},
            {"226","objective","目的","n","0"},
            {"227","pile","積み重ね","n","0"},
            {"228","shelter","避難","n","0"},
            {"229","trial","試み","n","0"},
            {"230","honor","名誉","n","0"},
            {"231","territory","領土","n","0"},
            {"232","frame","わく","n","0"},
            {"233","border","国境地帯","n","0"},
            {"234","statistics","統計","n","0"},
            {"235","enterprise","企業","n","0"},
            {"236","fee","謝礼","n","0"},
            {"237","load","荷物","n","0"},
            {"238","grain","穀物","n","0"},
            {"239","review","再検討","n","0"},
            {"240","prejudice","偏見","n","0"},
            {"241","strain","負担","n","0"},
            {"242","trap","わな","n","0"},
            {"243","temper","気性","n","0"},
            {"244","slave","奴隷","n","0"},
            {"245","wound","傷","n","0"},
            {"246","divorce","離婚","n","0"},
            {"247","tune","曲","n","0"},
            {"248","height","高さ","n","0"},
            {"249","faculty","学部","n","0"},
            {"250","span","期間","n","0"},
            {"251","guilty","有罪の","adj","0"},
            {"252","vital","必要な","adj","0"},
            {"253","rough","荒い","adj","0"},
            {"254","contemporary","現代の","adj","0"},
            {"255","annual","年に一度の","adj","0"},
            {"256","accustomed","慣れた","adj","0"},
            {"257","steady","しっかりした","adj","0"},
            {"258","dull","退屈させる","adj","0"},
            {"259","keen","鋭い","adj","0"},
            {"260","loose","ゆるい","adj","0"},
            {"261","delicate","繊細な","adj","0"},
            {"262","internal","内部の","adj","0"},
            {"263","casual","気楽な","adj","0"},
            {"264","mature","成熟した","adj","0"},
            {"265","concrete","具体的な","adj","0"},
            {"266","awful","ひどい","adj","0"},
            {"267","senior","先輩の","adj","0"},
            {"268","overall","全体的な","adj","0"},
            {"269","tight","きつい","adj","0"},
            {"270","prime","主要な","adj","0"},
            {"271","genuine","本物の","adj","0"},
            {"272","modest","控えめな","adj","0"},
            {"273","intimate","親密な","adj","0"},
            {"274","minimum","最小限の","adj","0"},
            {"275","sophisticated","高度な","adj","0"},
            {"276","latter","後者の","adj","0"},
            {"277","bitter","つらい","adj","0"},
            {"278","peculiar","独特の","adj","0"},
            {"279","passive","受動的な","adj","0"},
            {"280","ethnic","民族的な","adj","0"},
            {"281","precisely","正確に","adv","0"},
            {"282","meanwhile","その間に","adv","0"},
            {"283","altogether","完全に","adv","0"},
            {"284","lately","最近","adv","0"},
            {"285","barely","かろうじて","adv","0"},
            {"286","scacely","ほとんど～ない","adv","0"},
            {"287","accordingly","それ相応に","adv","0"},
            {"288","deliberately","わざと","adv","0"},
            {"289","beneath","～の下で","adv","0"},
            {"290","lonely","孤独だ","adv","0"},
            {"291","precious","貴重な","adv","0"},
            {"292","minor","小さい","adv","0"},
            {"293","generous","気前の良い","adv","0"},
            {"294","tropical","熱帯","adv","0"},
            {"295","vague","漠然とした","adv","0"},
            {"296","principal","主要な","adv","0"},
            {"297","numerous","たくさんの","adv","0"},
            {"298","rural","田舎の","adv","0"},
            {"299","temporary","一時的な","adv","0"},
            {"300","artificial","人工","adv","0"},
            {"301","visible","目に見える","adv","0"},

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
