package model;

public enum GameMode {
    PLAYER_VS_PLAYER(1, new PlayerReal(Seed.CROSS)),
    PLAYER_VS_AI_EASY(2, new PlayerAIEasy(Seed.CROSS)),
    PLAYER_VS_AI_HARD(3, new PlayerAIHard(Seed.CROSS));

    public int getOptionNum() {
        return optionNum;
    }

    public Player getPlayer() {
        return player;
    }

    private int optionNum;
    private Player player;

    GameMode(int option, Player playerClass) {
       this.optionNum = option;
       this.player = playerClass;
    }

    public static GameMode getByOptionNumber(int option) {
        for (GameMode mode : values()) {
            if (mode.getOptionNum() == option) {
                return mode;
            }
        }
        throw new IllegalArgumentException();
    }
}