public class KMP {

    public void preProcess(int[] prefix, char[] P){
        prefix[0] = 0;
        int cnt = 0;
        // since string starts from index 0, hence cnt + 1 - 1 is used here
        for(int i = 1; i < prefix.length; ++i){
            while(cnt > 0 && P[cnt + 1 - 1] != P[i]){
                cnt = prefix[cnt - 1];// prefix[0] is the first suffix
            }
            if(P[cnt + 1 - 1] == P[i]) cnt += 1;
            prefix[i] = cnt;          // note that i > cnt always
        }
    }

    public void kmp(String T, String P){
        int[] prefix = new int[P.length()];
        char[] pattern = P.toCharArray();
        char[] text = T.toCharArray();
        int m = pattern.length;
        int cnt = 0;                  // length of characters have been matched
        
        preProcess(prefix, pattern);

        for(int i = 0; i < text.length; ++i){
            while(cnt > 0 && pattern[cnt + 1 - 1] != text[i]){
                cnt = prefix[cnt - 1];// prefix[0] is first suffix with length 1
            }
            if(pattern[cnt + 1 - 1] == text[i]){
                cnt += 1;
            }
            if(cnt == m)              // find a match
            {
                System.out.println("The pattern occurs at " + (i - m + 1));
                cnt = prefix[m - 1];  // update cnt based on prefix array
            }
        }
    }
}
