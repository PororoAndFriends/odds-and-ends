# init setting
n,k = map(int,input().split())

global stuff
stuff = []
for _ in range(n):
    stuff.append(list(map(int,input().split())))

global dp
dp = [[-1 for _ in range(k+1)] for _ in range(n+1)]
for i in range(n+1):
    for j in range(k+1):
        if i == 0 or j == 0:
            dp[i][j] = 0

def knap(n,m):
    if dp[n][m] == -1:
        knap(n-1,m)
        if m >= stuff[n-1][0]:
            knap(n-1,m-stuff[n-1][0])
            dp[n][m] = max(dp[n-1][m], dp[n-1][m-stuff[n-1][0]]+stuff[n-1][1])
        else:
            dp[n][m] = dp[n-1][m]
    return dp[n][m]

# solv
print(knap(len(dp)-1, len(dp[0])-1))

# init setting
n,k = map(int,input().split())

global stuff
stuff = []
for _ in range(n):
    stuff.append(list(map(int,input().split())))

global dp
dp = [[-1 for _ in range(k+1)] for _ in range(n+1)]
for i in range(n+1):
    for j in range(k+1):
        if i == 0 or j == 0:
            dp[i][j] = 0

def knap(n,m):
    if dp[n][m] == -1:
        knap(n-1,m)
        if m >= stuff[n-1][0]:
            knap(n-1,m-stuff[n-1][0])
            dp[n][m] = max(dp[n-1][m], dp[n-1][m-stuff[n-1][0]]+stuff[n-1][1])
        else:
            dp[n][m] = dp[n-1][m]
    return dp[n][m]

# solv
print(knap(len(dp)-1, len(dp[0])-1))
