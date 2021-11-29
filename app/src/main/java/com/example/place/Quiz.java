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
            {"2","consignment","委託","n","0"},
            {"3","insecticide","虫眼鏡","n","0"},
            {"4","upholstery","室内装飾品","n","0"},
            {"5","javelin","ジャベリン","n","0"},
            {"6","cryptic","潜在的な","adj","0"},
            {"7","zest","熱意","n","0"},
            {"8","induction","誘導","n","0"},
            {"9","acreage","めちゃくちゃ","n","0"},
            {"10","forgery","偽造","n","0"},
            {"11","allay","和らげる","v","0"},
            {"12","ovary","卵巣","n","0"},
            {"13","glucose","グルコース","n","0"},
            {"14","pounce","急上昇","v","0"},
            {"15","diction","辞書","n","0"},
            {"16","smolder","スムーダー","v","0"},
            {"17","wicker","枝編み細工品","n","0"},
            {"18","judicious","賢明な","adj","0"},
            {"19","ruminate","轟々","v","0"},
            {"20","corollary","腐敗した","n","0"},
            {"21","slacken","スラッケン","v","0"},
            {"22","puberty","思春期","n","0"},
            {"23","fluffy","ふわふわ","adj","0"},
            {"24","forage","飼料","n","0"},
            {"25","esoteric","難病","adj","0"},
            {"26","muck","泥","n","0"},
            {"27","crochet","かぎ針編み","n","0"},
            {"28","redress","救う","v","0"},
            {"29","dribble","ドリブル","v","0"},
            {"30","fabrication","生成","n","0"},
            {"31","capricious","気まぐれな","adj","0"},
            {"32","insulation","絶縁","n","0"},
            {"33","voluble","太った","n","0"},
            {"34","balm","香ばしい","n","0"},
            {"35","cavalry","騎兵","n","0"},
            {"36","flail","羽毛","n","0"},
            {"37","cataclysm","カタシュズム","n","0"},
            {"38","detract","損なわれる","v","0"},
            {"39","demoralize","悪魔","v","0"},
            {"40","topography","トポグラフィー","n","0"},
            {"41","valor","勇気","n","0"},
            {"42","nitrate","硝酸塩","n","0"},
            {"43","extrovert","外向的な","n","0"},
            {"44","repudiate","否認する","v","0"},
            {"45","agrarian","農地","adj","0"},
            {"46","decentralize","分散化する","v","0"},
            {"47","outlay","費用","n","0"},
            {"48","assessor","賞賛","n","0"},
            {"49","abdicate","退院する","v","0"},
            {"50","scruffy","みすぼらしい","adj","0"},
            {"51","propagate","急降下する","v","0"},
            {"52","tempestuous","恒久","adj","0"},
            {"53","dingy","すすけた","adj","0"},
            {"54","thesaurus","シソーラス","n","0"},
            {"55","lisp","舌足らずの発音","v","0"},
            {"56","salient","臆病な","adj","0"},
            {"57","hoist","ホイスト","v","0"},
            {"58","facetious","ふざけた","adj","0"},
            {"59","haphazard","行き当たりばったり","n","0"},
            {"60","homogeneous","同種の","adj","0"},
            {"61","genus","属","n","0"},
            {"62","detest","忌み嫌う","v","0"},
            {"63","hedgehog","ハリネズミー","n","0"},
            {"64","oxide","酸化物","n","0"},
            {"65","ellipse","楕円","n","0"},
            {"66","douse","ゆがんで","v","0"},
            {"67","matron","マトリン","n","0"},
            {"68","dissertation","論文","n","0"},
            {"69","vector","ベクター","n","0"},
            {"70","postulate","公準","v","0"},
            {"71","nettle","イラクター","n","0"},
            {"72","supersede","スーパー","v","0"},
            {"73","garish","ぎこちない","adj","0"},
            {"74","pique","ピケ","n","0"},
            {"75","contravene","抑圧者","v","0"},
            {"76","rebuttal","反論","n","0"},
            {"77","reimburse","絶対に","v","0"},
            {"78","ineligible","不適格","adj","0"},
            {"79","zenith","天頂","n","0"},
            {"80","hermit","隠者","n","0"},
            {"81","whitewash","ホワイトウォッシュ","n","0"},
            {"82","leaden","リュードレン","adj","0"},
            {"83","angler","釣り人","n","0"},
            {"84","shoal","浅瀬","n","0"},
            {"85","airtight","気密","n","0"},
            {"86","embroidery","刺繍","n","0"},
            {"87","scum","スカム","v","0"},
            {"88","recuperate","復活する","v","0"},
            {"89","susceptibility","感受性","n","0"},
            {"90","onus","責任","n","0"},
            {"91","hedonism","ヘドニズム","n","0"},
            {"92","accession","廃棄物","n","0"},
            {"93","antagonize","拮抗","v","0"},
            {"94","fluctuate","変動する","v","0"},
            {"95","aggregation","集計","n","0"},
            {"96","daft","ダフト","adj","0"},
            {"97","affront","侮辱","v","0"},
            {"98","solvent","溶媒","n","0"},
            {"99","embryonic","胚の","n","0"},
            {"100","stallion","牡馬","n","0"},
            {"101","armistice","休戦","n","0"},
            {"102","jovial","陽気な","adj","0"},
            {"103","detrimental","有害な","adj","0"},
            {"104","incredulous","信頼できる","adj","0"},
            {"105","synopsis","概要","n","0"},
            {"106","alderman","アーダン","n","0"},
            {"107","pike","パイク","n","0"},
            {"108","interlock","インターロック","v","0"},
            {"109","proceed","続行","v","0"},
            {"110","ensure","確認","v","0"},
            {"111","interpret","解釈","v","0"},
            {"112","cease","停能","v","0"},
            {"113","spoil","甘やかす","v","0"},
            {"114","obey","従う","v","0"},
            {"115","eliminate","排除","v","0"},
            {"116","resist","抵抗","v","0"},
            {"117","accompany","同行","v","0"},
            {"118","weigh","重さ","v","0"},
            {"119","pursue","追及する","v","0"},
            {"120","demonstrate","為替える","v","0"},
            {"121","ruin","破滅","v","0"},
            {"122","regret","後悔","v","0"},
            {"123","attach","添えて","v","0"},
            {"124","reverse","逆行する","v","0"},
            {"125","restrict","制限","v","0"},
            {"126","capture","捕獲","v","0"},
            {"127","substitute","代わりの","v","0"},
            {"128","trace","痕跡","v","0"},
            {"129","interrupt","割り込み","v","0"},
            {"130","confront","人物","v","0"},
            {"131","arrest","逮捕","v","0"},
            {"132","stimulate","刺激","v","0"},
            {"133","depress","憂える","v","0"},
            {"134","crash","クラッシュ","v","0"},
            {"135","inspire","インスパイヤ","v","0"},
            {"136","specialize","専門化する","v","0"},
            {"137","cultivate","耕す","v","0"},
            {"138","fulfill","満たす","v","0"},
            {"139","transmit","送信する","v","0"},
            {"140","found","見つかった","v","0"},
            {"141","cheer","退手","v","0"},
            {"142","burst","バースト","v","0"},
            {"143","bow","弓","v","0"},
            {"144","dismiss","退出させる","v","0"},
            {"145","breed","繁殖","v","0"},
            {"146","prohibit","禁止","v","0"},
            {"147","oblige","義務","v","0"},
            {"148","invest","投資","v","0"},
            {"149","grasp","把握","v","0"},
            {"150","collapse","崩壊","v","0"},
            {"151","overlook","見落とし","v","0"},
            {"152","accuse","非難する","v","0"},
            {"153","proportion","割合","n","0"},
            {"154","contract","契約する","n","0"},
            {"155","appointment","予定","n","0"},
            {"156","treasure","宝物","n","0"},
            {"157","stock","株式","n","0"},
            {"158","facility","施設","n","0"},
            {"159","sum","和","n","0"},
            {"160","rank","ランクー","n","0"},
            {"161","democracy","民主主義","n","0"},
            {"162","emergency","緊急","n","0"},
            {"163","protest","抗議する","n","0"},
            {"164","track","追跡","n","0"},
            {"165","vehicle","車両","n","0"},
            {"166","stuff","もの","n","0"},
            {"167","row","行","n","0"},
            {"168","wheel","車輪","n","0"},
            {"169","dawn","夜明け","n","0"},
            {"170","welfare","福祉","n","0"},
            {"171","perspective","視点","n","0"},
            {"172","enthusiasm","熱意","n","0"},
            {"173","faith","信仰","n","0"},
            {"174","occupation","職業","n","0"},
            {"175","witness","目撃者","n","0"},
            {"176","kingdom","王国","n","0"},
            {"177","equivalent","同等","n","0"},
            {"178","objective","目的","n","0"},
            {"179","pile","パイル","n","0"},
            {"180","shelter","シェルター","n","0"},
            {"181","trial","トライアル","n","0"},
            {"182","honor","名誉","n","0"},
            {"183","territory","地域","n","0"},
            {"184","frame","フレーム","n","0"},
            {"185","border","国境","n","0"},
            {"186","statistics","統計","n","0"},
            {"187","enterprise","企業","n","0"},
            {"188","fee","手数料","n","0"},
            {"189","load","ロード","n","0"},
            {"190","grain","粒","n","0"},
            {"191","review","レビュー","n","0"},
            {"192","prejudice","偏見","n","0"},
            {"193","strain","歪み","n","0"},
            {"194","trap","トラップ","n","0"},
            {"195","temper","気性","n","0"},
            {"196","slave","奴隷","n","0"},
            {"197","wound","傷","n","0"},
            {"198","divorce","離婚","n","0"},
            {"199","tune","曲","n","0"},
            {"200","height","身長","n","0"},
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
