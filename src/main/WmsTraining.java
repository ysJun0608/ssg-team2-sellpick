package main;

public class WmsTraining {
    public static void logo() {
        try {
            String[] logo = {
                    "WWWWWWW                           WWWWWWWWMMMMMMMM               MMMMMMMM   SSSSSSSSSSSSSSS TTTTTTTTTTTTTTTTTTTTTTT                                      iiii                     iiii                                       ",
                    "W::::::W                           W::::::WM:::::::M             M:::::::M SS:::::::::::::::ST:::::::::::::::::::::T                                    i::::i                   i::::i                                      ",
                    "W::::::W                           W::::::WM::::::::M           M::::::::MS:::::SSSSSS::::::ST:::::::::::::::::::::T                                      iiii                     iiii                                       ",
                    "W::::::W                           W::::::WM:::::::::M         M:::::::::MS:::::S     SSSSSSST:::::TT:::::::TT:::::T                                                                                                           ",
                    " W:::::W           WWWWW           W:::::W M::::::::::M       M::::::::::MS:::::S            TTTTTT  T:::::T  TTTTTTrrrrr   rrrrrrrrr   aaaaaaaaaaaaa   iiiiiiinnnn  nnnnnnnn    iiiiiiinnnn  nnnnnnnn       ggggggggg   ggggg ",
                    "  W:::::W         W:::::W         W:::::W  M:::::::::::M     M:::::::::::MS:::::S                    T:::::T        r::::rrr:::::::::r  a::::::::::::a  i:::::in:::nn::::::::nn  i:::::in:::nn::::::::nn    g:::::::::ggg::::g ",
                    "   W:::::W       W:::::::W       W:::::W   M:::::::M::::M   M::::M:::::::M S::::SSSS                 T:::::T        r:::::::::::::::::r aaaaaaaaa:::::a  i::::in::::::::::::::nn  i::::in::::::::::::::nn  g:::::::::::::::::g ",
                    "    W:::::W     W:::::::::W     W:::::W    M::::::M M::::M M::::M M::::::M  SS::::::SSSSS            T:::::T        rr::::::rrrrr::::::r         a::::a  i::::inn:::::::::::::::n i::::inn:::::::::::::::ng::::::ggggg::::::gg",
                    "     W:::::W   W:::::W:::::W   W:::::W     M::::::M  M::::M::::M  M::::::M    SSS::::::::SS          T:::::T         r:::::r     r:::::r  aaaaaaa:::::a  i::::i  n:::::nnnn:::::n i::::i  n:::::nnnn:::::ng:::::g     g:::::g ",
                    "      W:::::W W:::::W W:::::W W:::::W      M::::::M   M:::::::M   M::::::M       SSSSSS::::S         T:::::T         r:::::r     rrrrrrraa::::::::::::a  i::::i  n::::n    n::::n i::::i  n::::n    n::::ng:::::g     g:::::g ",
                    "       W:::::W:::::W   W:::::W:::::W       M::::::M    M:::::M    M::::::M            S:::::S        T:::::T         r:::::r           a::::aaaa::::::a  i::::i  n::::n    n::::n i::::i  n::::n    n::::ng:::::g     g:::::g ",
                    "        W:::::::::W     W:::::::::W        M::::::M     MMMMM     M::::::M            S:::::S        T:::::T         r:::::r          a::::a    a:::::a  i::::i  n::::n    n::::n i::::i  n::::n    n::::ng::::::g    g:::::g ",
                    "         W:::::::W       W:::::::W         M::::::M               M::::::MSSSSSSS     S:::::S      TT:::::::TT       r:::::r          a::::a    a:::::a i::::::i n::::n    n::::ni::::::i n::::n    n::::ng:::::::ggggg:::::g ",
                    "          W:::::W         W:::::W          M::::::M               M::::::MS::::::SSSSSS:::::S      T:::::::::T       r:::::r           a:::::aaaa::::::a i::::::i n::::n    n::::ni::::::i n::::n    n::::n g::::::::::::::::g ",
                    "           W:::W           W:::W           M::::::M               M::::::MS:::::::::::::::SS       T:::::::::T       r:::::r            a::::::::::aa:::ai::::::i n::::n    n::::ni::::::i n::::n    n::::n  gg::::::::::::::g ",
                    "            WWW             WWW            MMMMMMMM               MMMMMMMM SSSSSSSSSSSSSSS         TTTTTTTTTTT       rrrrrrr             aaaaaaaaaa  aaaaiiiiiiii nnnnnn    nnnnnniiiiiiii nnnnnn    nnnnnn    gggggggg::::::g ",
                    "                                                                                                                                                                                                                      g:::::g "
            };

            for (String line : logo) {
                System.out.println(line);
                Thread.sleep(200); // 0.3초 대기
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
