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
            {"1","pillage","略奪","v","0"},
            {"2","maggot","ウジ","v","0"},
            {"3","scuffle","もみ合い","v","0"},
            {"4","tithe","十分の一","v","0"},
            {"5","veterinarian","獣医","v","0"},
            {"6","callous","冷淡な","v","0"},
            {"7","inert","不活性","v","0"},
            {"8","shifty","ずるそうな","v","0"},
            {"9","solvent","溶剤","v","0"},
            {"10","extradite","引き渡す","v","0"},
            {"11","laudable","称賛に値する","v","0"},
            {"12","efface","消す","v","0"},
            {"13","furtive","ひそかな","v","0"},
            {"14","inlet","入り江","v","0"},
            {"15","stocky","ずんぐりした","v","0"},
            {"16","blasphemy","冒涜","v","0"},
            {"17","protrude","突き出す","v","0"},
            {"18","fabrication","製作","v","0"},
            {"19","doldrums","低迷","v","0"},
            {"20","sulk","不機嫌","v","0"},
            {"21","matrimony","結婚生活","v","0"},
            {"22","node","ノード","v","0"},
            {"23","recoil","ひるむ","v","0"},
            {"24","consignment","委託","v","0"},
            {"25","plod","とぼとぼと歩く","v","0"},
            {"26","peppery","ピリッとした","v","0"},
            {"27","lusty","元気な","v","0"},
            {"28","deviate","外れる","v","0"},
            {"29","dune","砂丘","v","0"},
            {"30","emissary","使者","v","0"},
            {"31","arousal","覚醒","v","0"},
            {"32","stow","しまい込む","v","0"},
            {"33","cavalry","騎兵","v","0"},
            {"34","shin","すね","v","0"},
            {"35","prerogative","特権","v","0"},
            {"36","afoot","進行中の","v","0"},
            {"37","engender","生み出す","v","0"},
            {"38","whirlpool","渦","v","0"},
            {"39","valor","勇気","v","0"},
            {"40","wicker","枝編み細工","v","0"},
            {"41","crevice","裂け目","v","0"},
            {"42","delineation","描写","v","0"},
            {"43","duchy","公国","v","0"},
            {"44","vested","既得","v","0"},
            {"45","valiant","勇敢な","v","0"},
            {"46","exorbitant","法外な","v","0"},
            {"47","finery","華美な装飾品","v","0"},
            {"48","embankment","堤防","v","0"},
            {"49","ruminate","反芻","v","0"},
            {"50","gird","身構える","v","0"},
            {"51","waft","漂う","v","0"},
            {"52","immaterial","重要でない","v","0"},
            {"53","squeamish","うるさい　","v","0"},
            {"54","senile","老人性","v","0"},
            {"55","expound","詳しく説明する","v","0"},
            {"56","scrabble","引っかく","v","0"},
            {"57","despotic","専制的な","v","0"},
            {"58","swine","豚","v","0"},
            {"59","vantage","有意","v","0"},
            {"60","jostle","押し合う","v","0"},
            {"61","swindle","詐欺","v","0"},
            {"62","miscellaneous","雑多","v","0"},
            {"63","indemnity","賠償","v","0"},
            {"64","breadwinner","稼ぎ手","v","0"},
            {"65","barrow","手押し車","v","0"},
            {"66","fluffy","フワフワした","v","0"},
            {"67","rabies","狂犬病","v","0"},
            {"68","carousel","回転木馬","v","0"},
            {"69","deplorable","嘆かわしい　","v","0"},
            {"70","vestige","痕跡","v","0"},
            {"71","concave","凹面","v","0"},
            {"72","galley","ガレー船","v","0"},
            {"73","salutary","有益な","v","0"},
            {"74","exhortation","説教","v","0"},
            {"75","onus","責任","v","0"},
            {"76","connotation","含蓄","v","0"},
            {"77","pellet","ペレット","v","0"},
            {"78","dissipate","散らす","v","0"},
            {"79","hatchet","手おの","v","0"},
            {"80","whimper","泣く","v","0"},
            {"81","detriment","損害","v","0"},
            {"82","lucid","明快な","v","0"},
            {"83","topography","地形","v","0"},
            {"84","garnish","美辞麗句","v","0"},
            {"85","dermatologist","皮膚科医","v","0"},
            {"86","benevolence","慈悲","v","0"},
            {"87","projectile","発射体","v","0"},
            {"88","enshrine","祭る","v","0"},
            {"89","tenancy","借地","v","0"},
            {"90","leech","取り巻き","v","0"},
            {"91","upholstery","内装","v","0"},
            {"92","humdrum","平凡な","v","0"},
            {"93","ignoble","卑しい　","v","0"},
            {"94","depraved","堕落した","v","0"},
            {"95","sundry","諸","v","0"},
            {"96","fortitude","不屈の精神","v","0"},
            {"97","potassium","カリウム","v","0"},
            {"98","abate","弱まる","v","0"},
            {"99","dingy","薄汚い　","v","0"},
            {"100","mercenary","傭兵","v","0"},
            {"101","sultan","スルタン","v","0"},
            {"102","furlough","休暇","v","0"},
            {"103","intoxication","中毒","v","0"},
            {"104","canopy","キャノピー","v","0"},
            {"105","hearsay","伝聞","v","0"},
            {"106","peevish","気難しい　","v","0"},
            {"107","crease","しわ","v","0"},
            {"108","dejected","意気消沈した","v","0"},
            {"109","hoarse","かすれた","v","0"},
            {"110","dike","堤防","v","0"},
            {"111","tremulous","震える","v","0"},
            {"112","decadence","退廃","v","0"},
            {"113","amenable","従順な","v","0"},
            {"114","arable","耕地","v","0"},
            {"115","talisman","お守り","v","0"},
            {"116","opinionated","意固地な","v","0"},
            {"117","orchid","蘭","v","0"},
            {"118","poppy","ケシ","v","0"},
            {"119","insular","偏狭な","v","0"},
            {"120","confederation","連合","v","0"},
            {"121","squid","イカ","v","0"},
            {"122","dinghy","ディンギー","v","0"},
            {"123","serviceman","係員","v","0"},
            {"124","smolder","くすぶる","v","0"},
            {"125","ephemeral","はかない　","v","0"},
            {"126","pigment","顔料","v","0"},
            {"127","snooze","居眠り","v","0"},
            {"128","bile","胆汁","v","0"},
            {"129","articulation","関節","v","0"},
            {"130","squatter","無断居住者","v","0"},
            {"131","tentacle","触手","v","0"},
            {"132","wart","いぼ","v","0"},
            {"133","decoy","おとり","v","0"},
            {"134","outlay","支出","v","0"},
            {"135","escapade","いたずら","v","0"},
            {"136","consternation","仰天","v","0"},
            {"137","inadvertent","不注意な","v","0"},
            {"138","insurmountable","乗り越えられない","v","0"},
            {"139","serum","血清","v","0"},
            {"140","promiscuous","無差別","v","0"},
            {"141","carcinogen","発癌物質","v","0"},
            {"142","abhor","忌み嫌う","v","0"},
            {"143","overrate","高く評価し過ぎる","v","0"},
            {"144","mutiny","反乱","v","0"},
            {"145","incinerator","焼却炉","v","0"},
            {"146","adjunct","付属物","v","0"},
            {"147","aft","船尾に","v","0"},
            {"148","brevity","簡潔","v","0"},
            {"149","rummage","くまなく捜す","v","0"},
            {"150","disheveled","乱れた","v","0"},
            {"151","casket","棺","v","0"},
            {"152","negate","否定する","v","0"},
            {"153","nonchalant","無関心な","v","0"},
            {"154","grubby","汚い　","v","0"},
            {"155","plumage","羽","v","0"},
            {"156","impudent","厚かましい　","v","0"},
            {"157","encroach","侵害する","v","0"},
            {"158","lava","溶岩","v","0"},
            {"159","dab","軽くたたく","v","0"},
            {"160","decrepit","老朽化した","v","0"},
            {"161","antagonize","拮抗","v","0"},
            {"162","atheist","無神論者","v","0"},
            {"163","autism","自閉症","v","0"},
            {"164","suffrage","参政権","v","0"},
            {"165","acorn","ドングリ","v","0"},
            {"166","naught","無価値","v","0"},
            {"167","saturation","飽和","v","0"},
            {"168","pall","ポール","v","0"},
            {"169","eviction","立ち退き","v","0"},
            {"170","gully","深い溝","v","0"},
            {"171","terrestrial","地上の","v","0"},
            {"172","allay","和らげる","v","0"},
            {"173","welter","ごった返し","v","0"},
            {"174","xenophobia","外国人恐怖症","v","0"},
            {"175","leaden","鉛色の","v","0"},
            {"176","quay","岸壁","v","0"},
            {"177","wriggle","身をよじる","v","0"},
            {"178","daunt","威圧する","v","0"},
            {"179","municipality","市町村","v","0"},
            {"180","lenient","寛大な","v","0"},
            {"181","cursory","ぞんざいな","v","0"},
            {"182","wrest","奪い取る","v","0"},
            {"183","lethargic","無気力","v","0"},
            {"184","scowl","顔をしかめる","v","0"},
            {"185","attire","衣装","v","0"},
            {"186","molding","鋳型","v","0"},
            {"187","impertinent","生意気","v","0"},
            {"188","plumb","垂直","v","0"},
            {"189","subsistence","生存","v","0"},
            {"190","succinct","簡潔な","v","0"},
            {"191","rejoinder","返答","v","0"},
            {"192","trough","トラフ","v","0"},
            {"193","jeer","冷やかす","v","0"},
            {"194","chronology","年表","v","0"},
            {"195","polyglot","ポリグロット","v","0"},
            {"196","knell","弔鐘","v","0"},
            {"197","moot","議論の余地がある","v","0"},
            {"198","homogeneous","均質","v","0"},
            {"199","arbitration","仲裁","v","0"},
            {"200","bereaved","遺族","v","0"},
    };

    //手動でString[?][5]の?を入れる
    private String Verb[][] = new String[200][5];
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
