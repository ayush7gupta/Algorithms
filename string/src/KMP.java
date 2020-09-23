public class KMP {
    //pattern matching in O(n)
    // the idea is to compute lps for the pattern
    //lps means length of proper prefix which is also a suffix.
    //proper prefix means all the prefix excluding the original string
    // example for AAB prefix is => "", "A", "AA", "AAB" but proper prefix are only "", "A", "AA"

    // the pattern matching will be done according to lps in teh following manner:-
    // 1. We start comparison of pat[j] with j = 0 with characters of current window of text.
    // 2. We keep matching characters txt[i] and pat[j] and keep incrementing i and j while pat[j] and txt[i] keep matching.
    // 3. When we see a mismatch
    //      3.1 We know that characters pat[0..j-1] match with txt[i-j…i-1] (Note that j starts with 0 and increment it only when there is a match).
    //      3.2 We also know (from above definition) that lps[j-1] is count of characters of pat[0…j-1] that are both proper prefix and suffix.
    //      3.3 From above two points, we can conclude that we do not need to match these lps[j-1] characters with txt[i-j…i-1]
    //          because we know that these characters will anyway match.

    public static void main(String[] args){
        String text = "ABABABABABA";
        String pattern ="AB";

        KMP kmp = new KMP();
        kmp.kmpSearch(text, pattern);


    }

    public void kmpSearch(String text, String pattern){
        int lenPattern = pattern.length();
        int lenText = text.length();

        int[] lps = new int[lenPattern];
        int i=0;
        int j=0;

        //compute lps array
        computeLPS(pattern, lenPattern, lps);

        while(i < lenText){
            if(pattern.charAt(j) == text.charAt(i)){
                j++;
                i++;
            }
            if(j == lenPattern){
                System.out.println("Pattern found at index: " + (i-j));
                j = lps[j-1];
            }
            else if( i<lenText && pattern.charAt(j) != text.charAt(i)){
                // Do not match lps[0..lps[j-1]] characters,
                // they will match anyway
                if(j!=0){
                    j = lps[j-1];
                }
                else{
                    i =i+1;
                }
            }
        }
    }
    public void computeLPS(String s, int patternLength, int[] lps){
        int len =0;
        lps[0] =0;

        for(int i=1; i<patternLength;){
            if(s.charAt(i) == s.charAt(len)){
                len++;
                lps[i] =len;
                i++;
            }
            else{
                if(len !=0){
                    len = lps[len-1];

                }
                else{
                    lps[i] = len;
                    i++;
                }
            }
        }
    }


}
