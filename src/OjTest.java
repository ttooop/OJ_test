import java.util.*;
import java.util.stream.Stream;

public class OjTest {
    public static void main(String[] args) {
        /*int[] nums1={4,1,2};
        int[] nums2={1,3,4,2};
        int[] res=nextGreaterElement(nums1,nums2);
        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i]);
        }*/

        /*
        int[] nums={1,2,1};
        int[] res2=nextGreaterElement2(nums);
        for (int i = 0; i < res2.length; i++) {
            System.out.println(res2[i]);
        }*/

        /**
         * 476
         */
        //System.out.println(findComplement(9));

        /**
         * 436
         */
//        int[][] intervals={{3,4},{2,3},{1,2}};
//        int[] res=findRightInterval(intervals);
//        for (int i :
//                res) {
//            System.out.println(i);
//        }

        /**
         * 467
         */
        //System.out.println(findSubstringInWraproundString("zab"));

        /**
         * 166
         */
        //System.out.println(fractionToDecimal(2,3));
        /**
         * 165
         */
        //System.out.println(compareVersion("0.1","1.1"));
    }

    public static List<String> fizzBuzz(int n){
        List<String> res=new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if(i%3==0&&i%5==0){
                res.add("FizzBuzz");
            }else if (i%3==0){
                res.add("Fizz");
            }else if(i%5==0){
                res.add("Buzz");
            }else{
                res.add(String.valueOf(i));
            }
        }
        return res;
    }

    public static int fourSumCount(int[] A,int[] B,int[] C,int[] D)
    {
        int res=0,n=A.length;
        Map<Integer,Integer> m1=new HashMap<>();
        Map<Integer,Integer> m2=new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int sumab=A[i]+B[j],sumcd=C[i]+D[j];
                m1.put(sumab,m1.getOrDefault(sumab,0)+1);
                m2.put(sumcd,m2.getOrDefault(sumcd,0)+1);
            }
        }
        for (Integer a:m1.keySet()) {
            res+=m1.get(a)*m2.getOrDefault(-a,0);
        }
        return res;
    }

    public static int compareVersion(String version1,String version2){
        String[] v1=version1.split("\\.");
        String[] v2=version2.split("\\.");
        int i=0,j=0;
        while(i<v1.length||j<v2.length){
            int a=(i<v1.length)?Integer.parseInt(v1[i]):0;
            int b=(j<v2.length)?Integer.parseInt(v2[j]):0;
            if(a>b){
                return 1;
            }else if(a<b){
                return -1;
            }
            ++i;++j;
        }
        return 0;
    }

    public static String fractionToDecimal(int numerator,int denominator){
        long n=numerator,d=denominator;
        if(n==0L)
            return "0";
        StringBuilder builder=new StringBuilder();
        if(n<0){
            n=-n;
            if(d<0){
                d=-d;
            }else {
                builder.append('-');
            }
        }else if(d<0){
            d=-d;
            builder.append('-');
        }

        builder.append(n/d);
        long mod=n%d;
        if(mod==0)
            return builder.toString();
        builder.append('.');

        Map<Long,Integer> map=new HashMap<>();
        map.put(mod,builder.length());

        while(mod>0){
            mod=(mod<<3)+(mod<<1);
            builder.append(mod/d);
            mod%=d;
            if(map.containsKey(mod))
                break;
            else map.put(mod,builder.length());
        }

        if(mod>0){
            int index=map.get(mod);
            builder.insert(index,'(');
            builder.append(')');
        }
        return builder.toString();
    }

    public static int[] rearrangeBarcodes(int[] barcodes){
        Map<Integer,Integer> map=new HashMap<>();
        int[] ret=new int[barcodes.length];
        for (int a :
                barcodes) {
            map.put(a,map.getOrDefault(a,0)+1);
        }
        PriorityQueue<int[]> pq=new PriorityQueue<>((a,b)->b[1]-a[1]);
        int i=0;
        for (Map.Entry<Integer, Integer> e :
                map.entrySet()) {
            pq.offer(new int[]{e.getKey(),e.getValue()});
        }
        while (!pq.isEmpty()){
            int[] curr=pq.poll();
            if(i!=0&&ret[i-1]==curr[0]){
                int[] next=pq.poll();
                ret[i++]=next[0];
                next[1]--;
                if(next[1]>0)
                    pq.offer(next);
                pq.offer(curr);
            }else{
                ret[i++]=curr[0];
                curr[1]--;
                if(curr[1]>0){
                    pq.offer(curr);
                }
            }
        }
        return ret;
    }

    public static int maxProfit(int[] prices,int fee){
        int sold=0,hold=-prices[0];
        for (int price :
                prices) {
            int t = sold;
            sold=Math.max(sold,hold+price-fee);
            hold=Math.max(hold,t-price);
        }

        return sold;
    }

    public static int findSubstringInWraproundString(String p){
        int[] cnt=new int[26];
        int len=0;
        for (int i = 0; i < p.length(); i++) {
            if(i>0&&(p.charAt(i)==p.charAt(i-1)+1||p.charAt(i-1)-p.charAt(i)==25)){
                ++len;
            }
            else{
                len=1;
            }
            cnt[p.charAt(i)-'a']=Math.max(cnt[p.charAt(i)-'a'],len);
        }
        return Arrays.stream(cnt).sum();
    }

    public static int[] findRightInterval(int[][] intervals){
        int[] res=new int[intervals.length];
        int[] starts=new int[intervals.length];
        Map<Integer,Integer> m=new HashMap<>();
        for (int i = 0; i < intervals.length; i++) {
            starts[i]=intervals[i][0];
            m.put(intervals[i][0],i);
        }
        Arrays.sort(starts);
        for (int i = 0; i < intervals.length; i++) {
            int j;
            for (j = starts.length-1; j >=0 ; j--) {
                if(intervals[i][1]>starts[j]){
                    break;
                }
            }
            res[i]=((j<starts.length-1)?m.get(starts[j+1]):-1);
        }
        return res;
    }

    public static int findComplement(int num){
        int res=0,count=0;
        while(num!=0){
            int t=(num+1)%2;
            num/=2;
            res+=t*Math.pow(2,count++);
        }
        return res;
    }
    public static int[] nextGreaterElement(int[] nums1,int[] nums2){
        int[] res=new int[nums1.length];
        Map<Integer,Integer> m=new HashMap<>();
        for (int i = 0; i < nums2.length; i++) {
            m.put(nums2[i],-1);
            for (int j = i+1; j < nums2.length; j++) {
                if(nums2[j]>nums2[i]) {
                    m.put(nums2[i],nums2[j]);
                    break;
                }
            }
        }
        for (int i = 0; i <nums1.length; i++) {
            res[i]=m.get(nums1[i]);
        }
        return res;

    }


    public static int[] nextGreaterElement2(int[] nums){
        int[] res=new int[nums.length];
        int n=nums.length;
        Map<Integer,Integer> m=new HashMap<>();
        for (int i = 0; i < n; i++) {
            m.put(nums[i],-1);
            for (int j = i+1; j < i+n; j++) {
                if(nums[j%n]>nums[i]) {
                    m.put(nums[i],nums[j%n]);
                    break;
                }
            }
        }
        for (int i = 0; i< n; i++) {
            res[i]=m.get(nums[i]);
        }
        return res;

    }


}
