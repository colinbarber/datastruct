# *** RAISE TO THE POWER ***

# def Raise(base, exp):
#   if (exp <= 0):
#     return 1
#   else:
#     return base * Raise(base, exp-1)

# import math

# def Raise(base, exp):
#   if (exp == 0):
#     return 1
#   else:
#     half = Raise(base, math.floor(exp/2))
#     if ((exp % 2) == 0):
#       return half * half
#     else:
#       return base * half * half
    
# print(Raise(2,1))

# *** PALINDROME ***

def palindrome(s):
  length = len(s)
  if (length <= 1):
    return True
  elif ((s[0] == s[-1]) and palindrome(s[1:-1])):
    return True
  else: return False

print(palindrome("wraarw"))