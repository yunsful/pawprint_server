package pawprint.demo.domain.enums;

public enum Species {
    // 🐶 견종
    MALTESE("말티즈", "DOG"),
    POODLE("푸들", "DOG"),
    SHIH_TZU("시추", "DOG"),
    POMERANIAN("포메라니안", "DOG"),
    BICHON("비숑", "DOG"),
    YORKSHIRE_TERRIER("요크셔테리어", "DOG"),
    CHIHUAHUA("치와와", "DOG"),
    JINDO("진돗개", "DOG"),
    GOLDEN_RETRIEVER("골든리트리버", "DOG"),
    LABRADOR_RETRIEVER("래브라도리트리버", "DOG"),
    SHIBA_INU("시바견", "DOG"),
    HUSKY("허스키", "DOG"),
    SAMOYED("사모예드", "DOG"),
    BEAGLE("비글", "DOG"),
    BORDER_COLLIE("보더콜리", "DOG"),
    
    // 🐱 묘종
    PERSIAN("페르시안", "CAT"),
    SCOTTISH_FOLD("스코티시 폴드", "CAT"),
    SIAMESE("샴", "CAT"),
    BENGAL("뱅갈", "CAT"),
    RUSSIAN_BLUE("러시안 블루", "CAT"),
    MAINE_COON("메인쿤", "CAT"),
    SPHYNX("스핑크스", "CAT"),
    NORWEGIAN_FOREST("노르웨이 숲", "CAT"),
    AMERICAN_SHORTHAIR("아메리칸 숏헤어", "CAT"),
    KOREAN_SHORTHAIR("코리안 숏헤어", "CAT"),
    
    // 기타
    OTHER("기타", "OTHER");
    
    private final String displayName;
    private final String type; // DOG, CAT, OTHER 등
    
    Species(String displayName, String type) {
        this.displayName = displayName;
        this.type = type;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    public String getType() {
        return type;
    }
}
