public class Test {
    public static void main(String[] args) {
        System.out.println("Hello copilot!");
    }

    int sum(int a, int b) {
        return a + b;
    }

    int sum(int a, int b, int c) {
        return a + b + c;
    }

    int sum(int a, int b, int c, int d) {
        return a + b + c + d;
    }

    int sum(int a, int b, int c, int d, int e) {
        return a + b + c + d + e;
    }

    // make average function
    double average(int a, int b) {
        return (a + b) / 2.0;
    }

    // 뺄셈 함수 만들어줘
    int minus(int a, int b) {
        return a - b;
    }

    // k번째 소수 구하는 함수 만들어줘
    int prime(int k) {
        int count = 0;
        int i = 2;
        while (count < k) {
            if (isPrime(i)) {
                count++;
            }
            i++;
        }
        return i - 1;
    }

    // 소수가 맞는지 확인하는 함수 만들어줘
    boolean isPrime(int n) {
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    // 소수가 아닌 수를 찾는 함수 만들어줘
    int notPrime(int n) {
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return i;
            }
        }
        return -1;
    }


}

