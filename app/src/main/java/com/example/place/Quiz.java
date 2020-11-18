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
//            {"1","pillage","略奪","v","0"},
//            {"2","maggot","ウジ","v","0"},
//            {"3","scuffle","もみ合い","v","0"},
//            {"4","tithe","十分の一","v","0"},
//            {"5","veterinarian","獣医","v","0"},
//            {"6","callous","冷淡な","v","0"},
//            {"7","inert","不活性","v","0"},
//            {"8","shifty","ずるそうな","v","0"},
//            {"9","solvent","溶剤","v","0"},
//            {"10","extradite","引き渡す","v","0"},
//            {"11","laudable","称賛に値する","v","0"},
//            {"12","efface","消す","v","0"},
//            {"13","furtive","ひそかな","v","0"},
//            {"14","inlet","入り江","v","0"},
//            {"15","stocky","ずんぐりした","v","0"},
//            {"16","blasphemy","冒涜","v","0"},
//            {"17","protrude","突き出す","v","0"},
//            {"18","fabrication","製作","v","0"},
//            {"19","doldrums","低迷","v","0"},
//            {"20","sulk","不機嫌","v","0"},
//            {"21","matrimony","結婚生活","v","0"},
//            {"22","node","ノード","v","0"},
//            {"23","recoil","ひるむ","v","0"},
//            {"24","consignment","委託","v","0"},
//            {"25","plod","とぼとぼと歩く","v","0"},
//            {"26","peppery","ピリッとした","v","0"},
//            {"27","lusty","元気な","v","0"},
//            {"28","deviate","外れる","v","0"},
//            {"29","dune","砂丘","v","0"},
//            {"30","emissary","使者","v","0"},
//            {"31","arousal","覚醒","v","0"},
//            {"32","stow","しまい込む","v","0"},
//            {"33","cavalry","騎兵","v","0"},
//            {"34","shin","すね","v","0"},
//            {"35","prerogative","特権","v","0"},
//            {"36","afoot","進行中の","v","0"},
//            {"37","engender","生み出す","v","0"},
//            {"38","whirlpool","渦","v","0"},
//            {"39","valor","勇気","v","0"},
//            {"40","wicker","枝編み細工","v","0"},
//            {"41","crevice","裂け目","v","0"},
//            {"42","delineation","描写","v","0"},
//            {"43","duchy","公国","v","0"},
//            {"44","vested","既得","v","0"},
//            {"45","valiant","勇敢な","v","0"},
//            {"46","exorbitant","法外な","v","0"},
//            {"47","finery","華美な装飾品","v","0"},
//            {"48","embankment","堤防","v","0"},
//            {"49","ruminate","反芻","v","0"},
//            {"50","gird","身構える","v","0"},
//            {"51","waft","漂う","v","0"},
//            {"52","immaterial","重要でない","v","0"},
//            {"53","squeamish","うるさい　","v","0"},
//            {"54","senile","老人性","v","0"},
//            {"55","expound","詳しく説明する","v","0"},
//            {"56","scrabble","引っかく","v","0"},
//            {"57","despotic","専制的な","v","0"},
//            {"58","swine","豚","v","0"},
//            {"59","vantage","有意","v","0"},
//            {"60","jostle","押し合う","v","0"},
//            {"61","swindle","詐欺","v","0"},
//            {"62","miscellaneous","雑多","v","0"},
//            {"63","indemnity","賠償","v","0"},
//            {"64","breadwinner","稼ぎ手","v","0"},
//            {"65","barrow","手押し車","v","0"},
//            {"66","fluffy","フワフワした","v","0"},
//            {"67","rabies","狂犬病","v","0"},
//            {"68","carousel","回転木馬","v","0"},
//            {"69","deplorable","嘆かわしい　","v","0"},
//            {"70","vestige","痕跡","v","0"},
//            {"71","concave","凹面","v","0"},
//            {"72","galley","ガレー船","v","0"},
//            {"73","salutary","有益な","v","0"},
//            {"74","exhortation","説教","v","0"},
//            {"75","onus","責任","v","0"},
//            {"76","connotation","含蓄","v","0"},
//            {"77","pellet","ペレット","v","0"},
//            {"78","dissipate","散らす","v","0"},
//            {"79","hatchet","手おの","v","0"},
//            {"80","whimper","泣く","v","0"},
//            {"81","detriment","損害","v","0"},
//            {"82","lucid","明快な","v","0"},
//            {"83","topography","地形","v","0"},
//            {"84","garnish","美辞麗句","v","0"},
//            {"85","dermatologist","皮膚科医","v","0"},
//            {"86","benevolence","慈悲","v","0"},
//            {"87","projectile","発射体","v","0"},
//            {"88","enshrine","祭る","v","0"},
//            {"89","tenancy","借地","v","0"},
//            {"90","leech","取り巻き","v","0"},
//            {"91","upholstery","内装","v","0"},
//            {"92","humdrum","平凡な","v","0"},
//            {"93","ignoble","卑しい　","v","0"},
//            {"94","depraved","堕落した","v","0"},
//            {"95","sundry","諸","v","0"},
//            {"96","fortitude","不屈の精神","v","0"},
//            {"97","potassium","カリウム","v","0"},
//            {"98","abate","弱まる","v","0"},
//            {"99","dingy","薄汚い　","v","0"},
//            {"100","mercenary","傭兵","v","0"},
//            {"101","sultan","スルタン","v","0"},
//            {"102","furlough","休暇","v","0"},
//            {"103","intoxication","中毒","v","0"},
//            {"104","canopy","キャノピー","v","0"},
//            {"105","hearsay","伝聞","v","0"},
//            {"106","peevish","気難しい　","v","0"},
//            {"107","crease","しわ","v","0"},
//            {"108","dejected","意気消沈した","v","0"},
//            {"109","hoarse","かすれた","v","0"},
//            {"110","dike","堤防","v","0"},
//            {"111","tremulous","震える","v","0"},
//            {"112","decadence","退廃","v","0"},
//            {"113","amenable","従順な","v","0"},
//            {"114","arable","耕地","v","0"},
//            {"115","talisman","お守り","v","0"},
//            {"116","opinionated","意固地な","v","0"},
//            {"117","orchid","蘭","v","0"},
//            {"118","poppy","ケシ","v","0"},
//            {"119","insular","偏狭な","v","0"},
//            {"120","confederation","連合","v","0"},
//            {"121","squid","イカ","v","0"},
//            {"122","dinghy","ディンギー","v","0"},
//            {"123","serviceman","係員","v","0"},
//            {"124","smolder","くすぶる","v","0"},
//            {"125","ephemeral","はかない　","v","0"},
//            {"126","pigment","顔料","v","0"},
//            {"127","snooze","居眠り","v","0"},
//            {"128","bile","胆汁","v","0"},
//            {"129","articulation","関節","v","0"},
//            {"130","squatter","無断居住者","v","0"},
//            {"131","tentacle","触手","v","0"},
//            {"132","wart","いぼ","v","0"},
//            {"133","decoy","おとり","v","0"},
//            {"134","outlay","支出","v","0"},
//            {"135","escapade","いたずら","v","0"},
//            {"136","consternation","仰天","v","0"},
//            {"137","inadvertent","不注意な","v","0"},
//            {"138","insurmountable","乗り越えられない","v","0"},
//            {"139","serum","血清","v","0"},
//            {"140","promiscuous","無差別","v","0"},
//            {"141","carcinogen","発癌物質","v","0"},
//            {"142","abhor","忌み嫌う","v","0"},
//            {"143","overrate","高く評価し過ぎる","v","0"},
//            {"144","mutiny","反乱","v","0"},
//            {"145","incinerator","焼却炉","v","0"},
//            {"146","adjunct","付属物","v","0"},
//            {"147","aft","船尾に","v","0"},
//            {"148","brevity","簡潔","v","0"},
//            {"149","rummage","くまなく捜す","v","0"},
//            {"150","disheveled","乱れた","v","0"},
//            {"151","casket","棺","v","0"},
//            {"152","negate","否定する","v","0"},
//            {"153","nonchalant","無関心な","v","0"},
//            {"154","grubby","汚い　","v","0"},
//            {"155","plumage","羽","v","0"},
//            {"156","impudent","厚かましい　","v","0"},
//            {"157","encroach","侵害する","v","0"},
//            {"158","lava","溶岩","v","0"},
//            {"159","dab","軽くたたく","v","0"},
//            {"160","decrepit","老朽化した","v","0"},
//            {"161","antagonize","拮抗","v","0"},
//            {"162","atheist","無神論者","v","0"},
//            {"163","autism","自閉症","v","0"},
//            {"164","suffrage","参政権","v","0"},
//            {"165","acorn","ドングリ","v","0"},
//            {"166","naught","無価値","v","0"},
//            {"167","saturation","飽和","v","0"},
//            {"168","pall","ポール","v","0"},
//            {"169","eviction","立ち退き","v","0"},
//            {"170","gully","深い溝","v","0"},
//            {"171","terrestrial","地上の","v","0"},
//            {"172","allay","和らげる","v","0"},
//            {"173","welter","ごった返し","v","0"},
//            {"174","xenophobia","外国人恐怖症","v","0"},
//            {"175","leaden","鉛色の","v","0"},
//            {"176","quay","岸壁","v","0"},
//            {"177","wriggle","身をよじる","v","0"},
//            {"178","daunt","威圧する","v","0"},
//            {"179","municipality","市町村","v","0"},
//            {"180","lenient","寛大な","v","0"},
//            {"181","cursory","ぞんざいな","v","0"},
//            {"182","wrest","奪い取る","v","0"},
//            {"183","lethargic","無気力","v","0"},
//            {"184","scowl","顔をしかめる","v","0"},
//            {"185","attire","衣装","v","0"},
//            {"186","molding","鋳型","v","0"},
//            {"187","impertinent","生意気","v","0"},
//            {"188","plumb","垂直","v","0"},
//            {"189","subsistence","生存","v","0"},
//            {"190","succinct","簡潔な","v","0"},
//            {"191","rejoinder","返答","v","0"},
//            {"192","trough","トラフ","v","0"},
//            {"193","jeer","冷やかす","v","0"},
//            {"194","chronology","年表","v","0"},
//            {"195","polyglot","ポリグロット","v","0"},
//            {"196","knell","弔鐘","v","0"},
//            {"197","moot","議論の余地がある","v","0"},
//            {"198","homogeneous","均質","v","0"},
//            {"199","arbitration","仲裁","v","0"},
//            {"200","bereaved","遺族","v","0"},

            //systemEnglish
//            {"1","linger","残る","v","0"},
//            {"2","petroleum","石油","v","0"},
//            {"3","spouse","配偶者","v","0"},
//            {"4","mock","～をあざける","v","0"},
//            {"5","glitter","輝く","v","0"},
//            {"6","withstand","～に耐える","v","0"},
//            {"7","knit","～を編む","v","0"},
//            {"8","chew","噛む","v","0"},
//            {"9","scorn","軽蔑","v","0"},
//            {"10","queue","列","v","0"},
//            {"11","duplicate","～を複製する","v","0"},
//            {"12","tumor","腫瘍","v","0"},
//            {"13","applaud","～拍手する","v","0"},
//            {"14","outbreak","勃発","v","0"},
//            {"15","doctrine","教義","v","0"},
//            {"16","precaution","用心","v","0"},
//            {"17","census","国勢調査","v","0"},
//            {"18","jury","陪審員","v","0"},
//            {"19","swear","～と誓う","v","0"},
//            {"20","extinguish","～を消す","v","0"},
//            {"21","smash","~を粉々に砕く","v","0"},
//            {"22","diploma","卒業証書","v","0"},
//            {"23","vapor","蒸気","v","0"},
//            {"24","toll","通行料","v","0"},
//            {"25","lure","～誘い込む","v","0"},
//            {"26","lament","~を嘆く","v","0"},
//            {"27","slang","俗語","v","0"},
//            {"28","wither","しぼむ","v","0"},
//            {"29","tactics","戦術","v","0"},
//            {"30","embody","～を具現する","v","0"},
//            {"31","antiquity","古代","v","0"},
//            {"32","token","印","v","0"},
//            {"33","impair","～を低下させる","v","0"},
//            {"34","recite","～暗唱する","v","0"},
//            {"35","hinder","～を妨げる","v","0"},
//            {"36","subsidy","補助金","v","0"},
//            {"37","rattle","～をがたがた鳴らす","v","0"},
//            {"38","hospitality","歓迎","v","0"},
//            {"39","proclaim","~と宣言する","v","0"},
//            {"40","evade","～を逃れる","v","0"},
//            {"41","tumble","落ちる","v","0"},
//            {"42","autonomy","自主性","v","0"},
//            {"43","indignation","怒り","v","0"},
//            {"44","erect","～を築く","v","0"},
//            {"45","pledge","～を誓う","v","0"},
//            {"46","murmur","つぶやく","v","0"},
//            {"47","ornaments","飾り","v","0"},
//            {"48","entity","存在","v","0"},
//            {"49","deficit","赤字","v","0"},
//            {"50","rot","腐敗する","v","0"},
//            {"51","persecution","迫害","v","0"},
//            {"52","parasite","寄生生物","v","0"},
//            {"53","masterpiece","傑作","v","0"},
//            {"54","stroll","ぶらつく","v","0"},
//            {"55","shatter","~を粉々にする","v","0"},
//            {"56","chill","寒気","v","0"},
//            {"57","coward","臆病者","v","0"},
//            {"58","whip","~をむち打つ","v","0"},
//            {"59","soothe","～をなだめる","v","0"},
//            {"60","contemplate","～を考える","v","0"},
//            {"61","mortality","死亡","v","0"},
//            {"62","deteriorate","悪化する","v","0"},
//            {"63","burglar","強盗","v","0"},
//            {"64","revenue","収入","v","0"},
//            {"65","melancholy","憂鬱","v","0"},
//            {"66","degrade","～を悪化させる","v","0"},
//            {"67","menace","脅威","v","0"},
//            {"68","province","州","v","0"},
//            {"69","apparatus","装置","v","0"},
//            {"70","cuisine","料理","v","0"},
//            {"71","propaganda","宣伝","v","0"},
//            {"72","feat","偉業","v","0"},
//            {"73","ponder","～を熟考する","v","0"},
//            {"74","wrinkle","しわ","v","0"},
//            {"75","cosmos","宇宙","v","0"},
//            {"76","sue","～を訴える","v","0"},
//            {"77","transaction","取引","v","0"},
//            {"78","affirm","～と断言する","v","0"},
//            {"79","breeze","そよ風","v","0"},
//            {"80","lump","こぶ","v","0"},
//            {"81","germ","細菌","v","0"},
//            {"82","undone","元に戻らない","v","0"},
//            {"83","adore","～を崇拝する","v","0"},
//            {"84","summon","~を呼ぶ","v","0"},
//            {"85","reap","～を手に入れる","v","0"},
//            {"86","shrug","～をすくめる","v","0"},
//            {"87","irrigation","かんがい","v","0"},
//            {"88","slap","~をぴしゃりと打つ","v","0"},
//            {"89","insert","～を差し込む","v","0"},
//            {"90","deforestation","森林破壊","v","0"},
//            {"91","slaughter","～を虐殺する","v","0"},
//            {"92","preach","説教する","v","0"},
//            {"93","compile","～をまとめる","v","0"},
//            {"94","stake","賭け金","v","0"},
//            {"95","defy","～に逆らう","v","0"},
//            {"96","rhetoric","美辞麗句","v","0"},
//            {"97","retrieve","～を検索する","v","0"},
//            {"98","riot","暴動","v","0"},
//            {"99","pierce","～に穴をあける","v","0"},
//            {"100","specimen","標本","v","0"},
//            {"101","vow","～を誓う","v","0"},
//            {"102","rejoice","喜ぶ","v","0"},
//            {"103","undermine","～を弱める","v","0"},
//            {"104","temperament","気質","v","0"},
//            {"105","clarify","～を明らかにする","v","0"},
//            {"106","certificate","証明書","v","0"},
//            {"107","blur","～をぼやかす","v","0"},
//            {"108","peril","危機","v","0"},
//            {"109","barbarian","野蛮人","v","0"},
//            {"110","disguise","～を隠す","v","0"},
//            {"111","spur","～を駆り立てる","v","0"},
//            {"112","plight","苦境","v","0"},
//            {"113","prose","散文","v","0"},
//            {"114","curb","～を抑制する","v","0"},
//            {"115","monarch","君主","v","0"},
//            {"116","haunt","～付きまとわれる","v","0"},
//            {"117","reckon","～と考える","v","0"},
//            {"118","mourn","~を悲しむ","v","0"},
//            {"119","stun","びっくりさせる","v","0"},
//            {"120","choke","喉が詰まる","v","0"},
//            {"121","prophet","預言者","v","0"},
//            {"122","apprehension","不安","v","0"},
//            {"123","coverage","報道","v","0"},
//            {"124","supervisor","監督者","v","0"},
//            {"125","novelty","目新しさ","v","0"},
//            {"126","catastrophe","大災害","v","0"},
//            {"127","probe","探査機","v","0"},
//            {"128","roam","～を歩き回る","v","0"},
//            {"129","foresee","～を予知する","v","0"},
//            {"130","perplex","～困惑させる","v","0"},
//            {"131","aisle","通路","v","0"},
//            {"132","outlet","はけ口","v","0"},
//            {"133","aristocracy","貴族階級","v","0"},
//            {"134","discharge","～を放出する","v","0"},
//            {"135","predecessor","前任","v","0"},
//            {"136","stalk","～に忍び寄る","v","0"},
//            {"137","tyranny","圧制","v","0"},
//            {"138","testify","～証言する","v","0"},
//            {"139","stumble","つまづく","v","0"},
//            {"140","console","～をなぐさめる","v","0"},
//            {"141","flatter","～にお世辞を言う","v","0"},
//            {"142","narrative","話","v","0"},
//            {"143","intuition","直感","v","0"},
//            {"144","legacy","遺産","v","0"},
//            {"145","deficiency","欠乏","v","0"},
//            {"146","pavement","歩道","v","0"},
//            {"147","evoke","～を呼び起こす","v","0"},
//            {"148","suck","～を吸う","v","0"},
//            {"149","poke","～を突く","v","0"},
//            {"150","cradle","ゆりかご","v","0"},
//            {"151","discern","～を識別する","v","0"},

            //動作テスト用
            {"1","普遍的な","universal","adj","0"},
            {"2","気候","climate","n","0"},
            {"3","信じられない","incredible","adj","0"},
            {"4","～を身に着けている","wear","v","0"},
            {"5","～がどのようなものか説明する","describe","v","0"},
            {"6","短い","brief","adj","0"},
            {"7","～を折りたたむ","fold","v","0"},
            {"8","～を届ける","deliver","v","0"},
            {"9","発言","remark","n","0"},
            {"10","(ある決定や選択をするために)～を考量する","consider","v","0"},
            {"11","正確に","exactly","adv","0"},
            {"12","(ある一定の期間)続く","last","v","0"},
            {"13","態度","attitude","n","0"},
            {"14","～と思う","suppose","v","0"},
            {"15","めったに～ない","rarely","adv","0"},
            {"16","～をクビにする","fire","v","0"},
            {"17","裕福な","wealthy","adj","0"},
            {"18","偽の","false","v","0"},
            {"19","(未来のことについて)～と思う","expect","v","0"},
            {"20","～に警告する","warn","v","0"},
            {"21","喜び","pleasure","n","0"},
            {"22","請求書","bill","n","0"},
            {"23","平らな","flat","adj","0"},
            {"24","～をおだてる","flatter","v","0"},
            {"25","使用されていない","vacant","adj","0"},
            {"26","(時間、空間)を占める","occupy","v","0"},
            {"27","存在する","exist","v","0"},
            {"28","～に抵抗する","resist","v","0"},
            {"29","(～するだけの)力がある","capable","adj","0"},
            {"30","(理解や扱いが難しく)複雑な","complicated","adj","0"},
            {"31","前進、進歩","advance","n","0"},
            {"32","～と主張する、～を要求する","claim","v","0"},
            {"33","ありふれた、共通の","common","adj","0"},
            {"34","～をいじめる","bully","v","0"},
            {"35","～を借りている","owe","v","0"},
            {"36","～を否定する","deny","v","0"},
            {"37","目を覚まして","awake","adj","0"},
            {"38","増加する","increase","v","0"},
            {"39","徐々に","gradually","adv","0"},
            {"40","～を解決する","solve","v","0"},
            {"41","起源","origin","n","0"},
            {"42","組織","organization","n","0"},
            {"43","明白な、飾り気がない","plain","adj","0"},
            {"44","(主にプラスのことが)、～を(とても)驚かせる","amaze","v","0"},
            {"45","～について言及する","mention","v","0"},
            {"46","～を推測する","guess","v","0"},
            {"47","～を証明する、(tobe～)と判明する","prove","v","0"},
            {"48","～を良くする、良くなる","improve","v","0"},
            {"49","(紛争、対立など)を解決する、定住する","settle","v","0"},
            {"50","～を襲う","rob","v","0"},
            {"51","言い争う、(thatSV)～と主張する","argue","v","0"},
            {"52","余地","room","n","0"},
            {"53","不足","shortage","n","0"},
            {"54","膨張する","expand","v","0"},
            {"55","(幸福、意味など)を探し求める","seek","v","0"},
            {"56","～を容赦する","forgive","v","0"},
            {"57","物理的な、肉体の","physical","adj","0"},
            {"58","～を認識する","recognize","v","0"},
            {"59","古代の","ancient","adj","0"},
            {"60","～を受け入れる","accept","v","0"},
            {"61","～を傷つける、～を痛める","injure","v","0"},
            {"62","～を購入する","purchase","v","0"},
            {"63","無駄がない","efficient","adj","0"},
            {"64","(出来事や過程の)説明","account","n","0"},
            {"65","～を発明する","invent","v","0"},
            {"66","～を予防する","prevent","v","0"},
            {"67","辞職する","resign","v","0"},
            {"68","～を予測する","predict","v","0"},
            {"69","～を示している","indicate","v","0"},
            {"70","～を上げる","raise","v","0"},
            {"71","気分","temper","n","0"},
            {"72","量","amount","n","0"},
            {"73","手段","means","n","0"},
            {"74","出席している、存在している","present","adj","0"},
            {"75","～を輸出する","export","v","0"},
            {"76","好機","opportunity","n","0"},
            {"77","輸送(手段)","transportation","n","0"},
            {"78","～に強い印象を与える","impress","v","0"},
            {"79","(意見、感情など)を表現する","express","v","0"},
            {"80","経済的な、安上がりな","economical","adj","0"},
            {"81","(地域)社会、共同体","community","n","0"},
            {"82","社会","society","n","0"},
            {"83","～を分割する","divide","v","0"},
            {"84","個人、個体","individual","n","0"},
            {"85","きちんと","properly","adv","0"},
            {"86","(状況に応じて)適切な","appropriate","adj","0"},
            {"87","(旅行用の)荷物(の塊)","baggage","n","0"},
            {"88","(植物以外の)生き物","creature","n","0"},
            {"89","ことによると","possibly","adv","0"},
            {"90","独立した、自立した","independent","adj","0"},
            {"91","～を現実化する、～が分かる","realize","v","0"},
            {"92","(単語、歌、数字など)を暗記する","memorize","v","0"},
            {"93","農業","agriculture","n","0"},
            {"94","運賃","fare","n","0"},
            {"95","(社会の)慣習、習慣","custom","n","0"},
            {"96","～と推測する","estimate","v","0"},
            {"97","役割","role","n","0"},
            {"98","～を確立する","establish","v","0"},
            {"99","～を後悔している","regret","v","0"},
            {"100","洪水","flood","n","0"},
            {"101","報酬、褒美","reward","n","0"},
            {"102","～を罰する","punish","v","0"},
            {"103","～を一面に広げる","spread","v","0"},
            {"104","源","source","n","0"},
            {"105","～を申し出る、申し出","offer","v","0"},
            {"106","あがっている、神経の","nervous","adj","0"},
            {"107","～を怖がらせる","scare","v","0"},
            {"108","苦しんでいる、(嫌なこと)を経験する","suffer","v","0"},
            {"109","人工の","artificial","adj","0"},
            {"110","表面的な","superficial","adj","0"},
            {"111","十分な","sufficient","adj","0"},
            {"112","意義のある、重要な","significant","adj","0"},
            {"113","(仕事や義務など)を割り当てる","assign","v","0"},
            {"114","(社会的)問題、～を発行する","issue","n","0"},
            {"115","～を批判する、～を非難する","criticize","v","0"},
            {"116","～を要求する","demand","v","0"},
            {"117","～を推薦する","recommend","v","0"},
            {"118","～のふりをする","pretend","v","0"},
            {"119","～に出席する","attend","v","0"},
            {"120","～を拡張する、～を延ばす","extend","v","0"},
            {"121","(考え、感情など)を伝える","convey","v","0"},
            {"122","前の","previous","adj","0"},
            {"123","～にもかかわらず","despite","p","0"},
            {"124","(特に時間を要するもの)を完成する","complete","v","0"},
            {"125","(努力や忍耐によって、目的、\n仕事など)を達成する","accomplish","v","0"},
            {"126","事情","circumstance","n","0"},
            {"127","(基本となる)物質","substance","n","0"},
            {"128","振る舞う","behave","v","0"},
            {"129","～を公にする、～を出版する","publish","v","0"},
            {"130","攻撃的な","aggressive","adj","0"},
            {"131","現れる","appear","v","0"},
            {"132","～の入場・入学を許可する、～を認める","admit","v","0"},
            {"133","(公的機関などが)～に/～を許可する","permit","v","0"},
            {"134","(canを伴い)～する余裕がある","afford","v","0"},
            {"135","最終的に","eventually","adv","0"},
            {"136","～を取り除く","remove","v","0"},
            {"137","動機","motive","n","0"},
            {"138","～に食べ物を与える","feed","v","0"},
            {"139","～を操作する","operate","v","0"},
            {"140","支店、(学問の)分野","branch","n","0"},
            {"141","製品","product","n","0"},
            {"142","～を減らす","reduce","v","0"},
            {"143","(副詞を伴い)～を扱う、～を治療する","treat","v","0"},
            {"144","(病気、病人など)～を(完全に)治す","cure","v","0"},
            {"145","(データ、機械などが)正確な","accurate","adj","0"},
            {"146","好奇心の強い、奇妙な","curious","adj","0"},
            {"147","安全な、～を確保する","secure","adj","0"},
            {"148","衝動","impulse","n","0"},
            {"149","(問題、誤解などが)生じる","arise","v","0"},
            {"150","～を褒める","praise","v","0"},
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
