def swap(arg1, arg2):
    temp = arg1
    arg1 = arg2
    arg2 = temp
    return arg1, arg2


var1 = 1
var2 = 2
print "var1: %i var2: %i" % (var1, var2)

var1, var2 = swap(var1,var2) 
print "var1: %i var2: %i" % (var1, var2)

#var1, var2 = var2, var1
#print "var1: %i var2: %i" % (var1, var2)














