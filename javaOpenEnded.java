import java.util.*;

class Global
{
        public char chess[][] = new char[8][8];
        public int player=0;
        public boolean play,q,blackking=true,whiteking=true;
                
        public void display()
        {
                int k=8;
                System.out.println("\n\nChess Board Display");
                System.out.print("-------------------\n\n");
                System.out.println("    a   b   c   d   e   f   g   h ");
                System.out.println("   --- --- --- --- --- --- --- ---");
                for(int i=0;i<8;i++)
                {
                        System.out.print(k+" ");
                        for(int j=0;j<8;j++)
                        {
                                System.out.print("| "+chess[i][j]+" ");
                        }
                        System.out.println("|\n   --- --- --- --- --- --- --- ---");
                        k--;
                }
                System.out.print("\n\n");
        }
}

class Game extends Global
{
        char a,b,c,d,temp;
        String str;

        public Game()
        {
                chess[0][0]='r'; chess[7][0]='R';
                chess[0][1]='n'; chess[7][1]='N';
                chess[0][2]='b'; chess[7][2]='B';
                chess[0][3]='q'; chess[7][3]='Q';
                chess[0][4]='k'; chess[7][4]='K';
                chess[0][5]='b'; chess[7][5]='B';
                chess[0][6]='n'; chess[7][6]='N';
                chess[0][7]='r'; chess[7][7]='R';
                for(int i=1;i<7;i++)
                        for(int j=0;j<8;j++)
                        {
                                if(i==1)
                                        chess[1][j]='p';
                                else if(i==6)
                                        chess[6][j]='P';
                                else chess[i][j]=' ';
                        }
        }
                        
        public boolean capitalWhite(char ch)
        {
                if(ch=='R'||ch=='N'||ch=='B'||ch=='Q'||ch=='K'||ch=='P')
                        return true;
                return false;
        }
        
        public boolean smallBlack(char ch)
        {
                if(ch=='r'||ch=='n'||ch=='b'||ch=='q'||ch=='k'||ch=='p')
                        return true;
                return false;
        }

        public void check()
        {
                blackking=whiteking=false;
                for(int i=0;i<8;i++)
                        for(int j=0;j<8;j++)
                        {
                                if(chess[i][j]=='k')
                                        blackking=true;
                                if(chess[i][j]=='K')
                                        whiteking=true;
                        }
        }
        
        public void play()
        {
                Scanner input = new Scanner(System.in);
                System.out.print("\nEnter the first Player (black = 1) or (white = 2) : ");
                player=input.nextInt();
                if(player==1)
                        play=true; //black
                else
                        play=false; //white
                while(true)
                {
                        check();
                        display();
                        if(!(blackking&&whiteking))
                        {
                                if(!play)
                                        System.out.println("\n\nThe Winner is player 1\n\n");
                                else
                                        System.out.println("\n\nThe Winner is player 2\n\n");
                                return;
                        }
                        if(play)
                                System.out.print("Enter the player 1 (black) move : ");
                        else
                                System.out.print("Enter the player 2 (white) move : ");
                        str=input.next();
                        a=str.charAt(0);
                        int a1=8-Character.getNumericValue(a);
                        b=str.charAt(1);
                        int b1=Character.getNumericValue(b)-10;
                        temp=str.charAt(2);
                        c=str.charAt(3);
                        int c1=8-Character.getNumericValue(c);
                        d=str.charAt(4);
                        int d1=Character.getNumericValue(d)-10;
                        move(a1,b1,c1,d1);
                        play=!play;
                }
        }
        
        public void move(int a, int b, int c, int d)
        {
                if(smallBlack(chess[a][b])&&play)
                {
                        switch(chess[a][b])
                        {
                                case 'r' : rook(a,b,c,d); break;
                                case 'n' : nhorse(a,b,c,d); break;
                                case 'b' : bmanthri(a,b,c,d); break;
                                case 'q' : queen(a,b,c,d); break;
                                case 'k' : king(a,b,c,d); break;
                                case 'p' : pawn(a,b,c,d); break;
                        }
                }
                else if(capitalWhite(chess[a][b])&&!play)
                {
                        switch(chess[a][b])
                        {
                                case 'R' : rook(a,b,c,d); break;
                                case 'N' : nhorse(a,b,c,d); break;
                                case 'B' : bmanthri(a,b,c,d); break;
                                case 'Q' : queen(a,b,c,d); break;
                                case 'K' : king(a,b,c,d); break;
                                case 'P' : pawn(a,b,c,d); break;
                        }
                }
                else invalid();
        }
        
        public void invalid()
        {
                if(!q)
                {
                        System.out.print("Invalid Input\nEnter the move again...\n");
                        play=!play;
                }
        }
        
        public void gomove(int ti, int tj, int i, int j)
        {
                chess[ti][tj]=chess[i][j];
                chess[i][j]=' ';
                q=false;
        }
        
        public void rook(int ri, int rj, int i, int j)
        {
                int ti,tj;
                if((!(ri==i||rj==j))||(ri==i&&rj==j))
                        invalid();
                if(rj==j&&ri>i)
                {
                        ti=ri-1;
                        while((ti>i)&&chess[ti][j]==' ')
                                ti--;
                        if(ti==i)
                        {
                                if((!play&&smallBlack(chess[ti][j]))||(play&&capitalWhite(chess[ti][j])))
                                {
                                        gomove(ti,j,ri,rj);
                                        return ;
                                }
                                else if((!play&&capitalWhite(chess[ti][j]))||(play&&smallBlack(chess[ti][j])))
                                        invalid();
                                else if(chess[ti][j]==' ')
                                {
                                        gomove(ti,j,ri,rj);
                                        return ;
                                }
                        }
                        else if(ti>i)
                                invalid();                       
                }
                if(rj==j&&ri<i)
                {
                        ti=ri+1;
                        while((ti<i)&&chess[ti][j]==' ')
                                ti++;
                        if(ti==i)
                        {
                                if((!play&&smallBlack(chess[ti][j]))||(play&&capitalWhite(chess[ti][j])))
                                {
                                        gomove(ti,j,ri,rj);
                                        return ;
                                }
                                else if((!play&&capitalWhite(chess[ti][j]))||(play&&smallBlack(chess[ti][j])))
                                        invalid();
                                else if((!play&&chess[ti][j]==' ')||(play&&chess[ti][j]==' '))
                                {
                                        gomove(ti,j,ri,rj);
                                        return ;
                                }
                        }
                        else if(ti<i)
                                invalid();
                }
                if(ri==i&&rj>j)
                {
                        tj=rj-1;
                        while((tj>j)&&chess[i][tj]==' ')
                                tj--;
                        if(tj==j)
                        {
                                if((!play&&smallBlack(chess[i][tj]))||(play&&capitalWhite(chess[i][tj])))
                                {
                                        gomove(i,tj,ri,rj);
                                        return ;
                                }
                                else if((!play&&capitalWhite(chess[i][tj]))||(play&&smallBlack(chess[i][tj])))
                                        invalid();
                                else if(chess[i][tj]==' ')
                                {
                                        gomove(i,tj,ri,rj);
                                        return ;
                                }
                        }
                        else if(tj>j)
                                invalid();
                }
                if(ri==i&&rj<j)
                {
                        tj=rj+1;
                        while((tj<j)&&chess[i][tj]==' ')
                                tj++;
                        if(tj==j)
                        {
                                if((!play&&smallBlack(chess[i][tj]))||(play&&capitalWhite(chess[i][tj])))
                                {
                                        gomove(i,tj,ri,rj);
                                        return ;
                                }
                                else if((!play&&capitalWhite(chess[i][tj]))||(play&&smallBlack(chess[i][tj])))
                                        invalid();
                                else if((!play&&chess[i][tj]==' ')||(play&&chess[i][tj]==' '))
                                {
                                        gomove(i,tj,ri,rj);
                                        return ;
                                }
                        }
                        else if(tj<j)
                                invalid();
                }
        }
        
        public void horse(int hi, int hj, int i, int j)
        {
                if((play&&(capitalWhite(chess[i][j])||chess[i][j]==' '))||(!play&&(smallBlack(chess[i][j])||chess[i][j]==' ')))
                        gomove(i,j,hi,hj);
        }
        
        public void nhorse(int hi, int hj, int i, int j)
        {
                if(i+2==hi&&j+1==hj)
                        horse(hi,hj,i,j);
                else if(i+2==hi&&j==hj+1)
                        horse(hi,hj,i,j);
                else if(i+1==hi&&j+2==hj)
                        horse(hi,hj,i,j);
                else if(i+1==hi&&j==hj+2)
                        horse(hi,hj,i,j);
                else if(i==hi+1&&j+2==hj)
                        horse(hi,hj,i,j);
                else if(i==hi+1&&j==hj+2)
                        horse(hi,hj,i,j);
                else if(i==hi+2&&j+1==hj)
                        horse(hi,hj,i,j);
                else if(i==hi+2&&j==hj+1)
                        horse(hi,hj,i,j);
                else invalid();
        }
        
        public void bmanthri(int bi, int bj, int i, int j)
        {
                int ti=bi, tj=bj;
                if(i<bi&&j<bj)
                {
                        ti--; tj--;
                        while(chess[ti][tj]==' '&&ti>i&&tj>j)
                        {
                                ti--; tj--;
                        }
                        if(ti==i&&tj==j)
                        {
                                if((play&&(capitalWhite(chess[i][j])||chess[i][j]==' '))||(!play&&(smallBlack(chess[i][j]))||chess[i][j]==' '))
                                        gomove(i,j,bi,bj);
                                else invalid();
                        }
                        else invalid();
                }
                else if(i<bi&&j>bj)
                {
                        ti--; tj++;
                        while(chess[ti][tj]==' '&&ti>i&&tj<j)
                        {
                                ti--; tj++;
                        }
                        if(ti==i&&tj==j)
                        {
                                if((play&&(capitalWhite(chess[i][j])||chess[i][j]==' '))||(!play&&(smallBlack(chess[i][j]))||chess[i][j]==' '))
                                        gomove(i,j,bi,bj);
                                else invalid();
                        }
                        else invalid();
                }
                else if(i>bi&&j>bj)
                {
                        ti++; tj++;
                        while(chess[ti][tj]==' '&&ti<i&&tj<j)
                        {
                                ti++; tj++;
                        }
                        if(ti==i&&tj==j)
                        {
                                if((play&&(capitalWhite(chess[i][j])||chess[i][j]==' '))||(!play&&(smallBlack(chess[i][j]))||chess[i][j]==' '))
                                        gomove(i,j,bi,bj);
                                else invalid();
                        }
                        else invalid();
                }
                else if(i>bi&&j<bj)
                {
                        ti++; tj--;
                        while(chess[ti][tj]==' '&&ti<i&&tj>j)
                        {
                                ti++; tj--;
                        }
                        if(ti==i&&tj==j)
                        {
                                if((play&&(capitalWhite(chess[i][j])||chess[i][j]==' '))||(!play&&(smallBlack(chess[i][j]))||chess[i][j]==' '))
                                        gomove(i,j,bi,bj);
                                else invalid();
                        }
                        else invalid();
                }
                else invalid();
        }
        
        public void queen(int qi, int qj, int i, int j)
        {
                q=true;
                if(q)
                        rook(qi,qj,i,j);
                if(q)
                        nhorse(qi,qj,i,j);
                if(q)
                        bmanthri(qi,qj,i,j);
                if(q)
                        king(qi,qj,i,j);
                if(q)
                        invalid();
                q=false;
        }
        
        public void king(int ki, int kj, int i, int j)
        {
                boolean moved=false;
                if(ki+1==i||ki==i+1)
                {
                        if(j+1==kj||j==kj||j-1==kj)
                        {
                                if(play&&(capitalWhite(chess[i][j])||chess[i][j]==' '))
                                {
                                        gomove(i,j,ki,kj);
                                        moved=true;
                                }
                                else if(!play&&(smallBlack(chess[i][j])||chess[i][j]==' '))
                                {
                                        gomove(i,j,ki,kj);
                                        moved=true;
                                }
                        }
                }
                else if(ki==i)
                {
                        if(j+1==kj||j-1==kj)
                        {
                                if(play&&(capitalWhite(chess[i][j])||chess[i][j]==' '))
                                {
                                        gomove(i,j,ki,kj);
                                        moved=true;
                                }
                                else if(!play&&(smallBlack(chess[i][j])||chess[i][j]==' '))
                                {
                                        gomove(i,j,ki,kj);
                                        moved=true;
                                }
                        }
                }
                if(!moved)
                        invalid();
        }
        
        public void pawn(int pi, int pj, int i, int j)
        {
                if(!play&&(pi==6&&i==4&&chess[5][j]==' '&&chess[4][j]==' '&&pj==j))
                {
                        gomove(i,j,pi,pj);
                        return ;
                }
                if(!play&&(pi==i+1))
                {
                        if(pj==j&&chess[i][j]==' ')
                        {
                                gomove(i,j,pi,pj);
                                return ;
                        }
                        else if(pj==j+1||pj==j-1)
                        {
                                if(smallBlack(chess[i][j]))
                                {
                                        gomove(i,j,pi,pj);
                                        return ;
                                }
                                else invalid();
                        }
                        else invalid();
                }
                if(play&&(pi==1&&i==3&&chess[2][j]==' '&&chess[3][j]==' '&&pj==j))
                {
                        gomove(i,j,pi,pj);
                        return ;
                }
                if(play&&(pi+1==i))
                {
                        if(pj==j&&chess[i][j]==' ')
                        {
                                gomove(i,j,pi,pj);
                                return ;
                        }
                        else if(pj==j+1||pj==j-1)
                        {
                                if(capitalWhite(chess[i][j]))
                                {
                                        gomove(i,j,pi,pj);
                                        return ;
                                }
                                else invalid();
                        }
                        else invalid();
                }
                else invalid();
        }
}

class javaOpenEnded
{
        public static void main(String []arg)
        {
                int choice;
                Scanner input = new Scanner(System.in);
                System.out.println("\n\n\n        CHESS GAME\n        ----- ----");
                while(true)
                {
                        System.out.print("\n\n\n1. Play\n2. Help\n3. Exit\nEnter the choice : ");
                        choice=input.nextInt();
                        while(choice<1||choice>3)
                        {
                                System.out.print("\nEnter the Valid choice : ");
                                choice=input.nextInt();
                        }
                        if(choice==1)
                        {
                                Game g = new Game();
                                g.play();
                                System.out.println("\n\n\nPlay again !!!\n\n\n");
                        }
                        if(choice==2)
                        {
                                System.out.println("\n\nAbout -\n-----\n\nMovements syntax -\n--------- ------\nab-cd");
                                System.out.println("\nConstraints -\n-----------");
                                System.out.println("a and c ranges from 1 to 8.");
                                System.out.println("b and d ranges from a to h.");
                                System.out.println("ab and cd values should not be equal. (i.e., should not be in same place ).");
                                System.out.println("Only small letters can be used. And there shouldn't be any space in between.");
                                System.out.println("\nExample -\n-------\n2b-6d");
                                System.out.println("\nEnter the first Player : ( input should be 1 or 2 )");
                                System.out.println("\nLower case characters represents BLACK.\nUPPER case characters represents WHITE.");
                                System.out.println("\n\nGet Ready to Play ...");
                        }
                        if(choice==3)
                        {
                                System.out.println("\nBye Bye ...\n");
                                System.exit(0);
                        }
                }
        }
}
