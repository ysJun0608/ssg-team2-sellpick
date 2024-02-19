package config.printWMSTraing;

public class WmsTraining {
    public static void logo() {
        try {
            String[] logo = {
                    "WWWWWWW                           WWWWWWWWMMMMMMMM               MMMMMMMM   SSSSSSSSSSSSSSS T",
                    "W::::::W                           W::::::WM:::::::M             M:::::::M SS:::::::::::::::S",
                    "W::::::W                           W::::::WM::::::::M           M::::::::MS:::::SSSSSS::::::S",
                    "W::::::W                           W::::::WM:::::::::M         M:::::::::MS:::::S     SSSSSSS",
                    " W:::::W           WWWWW           W:::::W M::::::::::M       M::::::::::MS:::::S            ",
                    "  W:::::W         W:::::W         W:::::W  M:::::::::::M     M:::::::::::MS:::::S            ",
                    "   W:::::W       W:::::::W       W:::::W   M:::::::M::::M   M::::M:::::::M S::::SSSS         ",
                    "    W:::::W     W:::::::::W     W:::::W    M::::::M M::::M M::::M M::::::M  SS::::::SSSSS    ",
                    "     W:::::W   W:::::W:::::W   W:::::W     M::::::M  M::::M::::M  M::::::M    SSS::::::::SS  ",
                    "      W:::::W W:::::W W:::::W W:::::W      M::::::M   M:::::::M   M::::::M       SSSSSS::::S ",
                    "       W:::::W:::::W   W:::::W:::::W       M::::::M    M:::::M    M::::::M            S:::::S",
                    "        W:::::::::W     W:::::::::W        M::::::M     MMMMM     M::::::M            S:::::S",
                    "         W:::::::W       W:::::::W         M::::::M               M::::::MSSSSSSS     S:::::S",
                    "          W:::::W         W:::::W          M::::::M               M::::::MS::::::SSSSSS:::::S",
                    "           W:::W           W:::W           M::::::M               M::::::MS:::::::::::::::SS ",
                    "            WWW             WWW            MMMMMMMM               MMMMMMMM SSSSSSSSSSSSSSS   ",
                    "",
                    "",
                    "TTTTTTTTTTTTTTTTTTTTTT                                      iiii                     iiii                                      ",
                    "T:::::::::::::::::::::T                                    i::::i                   i::::i                                     ",
                    "T:::::::::::::::::::::T                                      iiii                     iiii                                      ",
                    "T:::::TT:::::::TT:::::T                                                                                                          ",
                    "TTTTTT  T:::::T  TTTTTTrrrrr   rrrrrrrrr   aaaaaaaaaaaaa   iiiiiiinnnn  nnnnnnnn    iiiiiiinnnn  nnnnnnnn       ggggggggg   ggggg",
                    "        T:::::T        r::::rrr:::::::::r  a::::::::::::a  i:::::in:::nn::::::::nn  i:::::in:::nn::::::::nn    g:::::::::ggg::::g",
                    "        T:::::T        r:::::::::::::::::r aaaaaaaaa:::::a  i::::in::::::::::::::nn  i::::in::::::::::::::nn  g:::::::::::::::::g",
                    "        T:::::T        rr::::::rrrrr::::::r         a::::a  i::::inn:::::::::::::::n i::::inn:::::::::::::::ng::::::ggggg::::::g",
                    "        T:::::T         r:::::r     r:::::r  aaaaaaa:::::a  i::::i  n:::::nnnn:::::n i::::i  n:::::nnnn:::::ng:::::g     g:::::g",
                    "        T:::::T         r:::::r     rrrrrrraa::::::::::::a  i::::i  n::::n    n::::n i::::i  n::::n    n::::ng:::::g     g:::::g",
                    "        T:::::T         r:::::r           a::::aaaa::::::a  i::::i  n::::n    n::::n i::::i  n::::n    n::::ng:::::g     g:::::g",
                    "        T:::::T         r:::::r          a::::a    a:::::a  i::::i  n::::n    n::::n i::::i  n::::n    n::::ng::::::g    g:::::g",
                    "      TT:::::::TT       r:::::r          a::::a    a:::::a i::::::i n::::n    n::::ni::::::i n::::n    n::::ng:::::::ggggg:::::g",
                    "      T:::::::::T       r:::::r           a:::::aaaa::::::a i::::::i n::::n    n::::ni::::::i n::::n    n::::n g::::::::::::::::g",
                    "      T:::::::::T       r:::::r            a::::::::::aa:::ai::::::i n::::n    n::::ni::::::i n::::n    n::::n  gg::::::::::::::g",
                    "      TTTTTTTTTTT       rrrrrrr             aaaaaaaaaa  aaaaiiiiiiii nnnnnn    nnnnnniiiiiiii nnnnnn    nnnnnn    gggggggg::::::g ",
                    "                                                                                                                          g:::::g ",
                    "                                                                                                              gggggg      g:::::g ",
                    "                                                                                                              g:::::gg   gg:::::g ",
                    "                                                                                                               g::::::ggg:::::::g ",
                    "                                                                                                                gg:::::::::::::g  ",
                    "                                                                                                                  ggg::::::ggg    ",
                    "                                                                                                                     gggggg       ",
                    "                                                                                                                                   ",
                    "                                                                                                                                   "
            };

            for (String line : logo) {
                System.out.println(line);
                Thread.sleep(100); // 0.3초 대기
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
