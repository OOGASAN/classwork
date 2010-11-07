# prove selection sort is unstable

from random import randint,random,shuffle

# list of random numbers!
ints = list(set([randint(0,10) for i in range(9)]))
shuffle(ints)

indx_to_dup = randint(0,9)
num_to_dup = ints[indx_to_dup]
indx_to_insert = randint(0,len(ints))

ints.insert(indx_to_insert, ints[indx_to_dup])
print 'dup %s at index %s' % (ints[indx_to_dup], indx_to_dup)
print 'insert dup %s at index: %s' % (ints[indx_to_dup], indx_to_insert)
print ints
	
tups = []	
tups_to_compare = []
for i in ints:
	elem2 = random()
	tups.append((i,elem2))	

# check =  get [1] for first instance of num_to_dup in tups		

# tups = selection_sort(tups by [0])

# if [1] for first instance of num_to_dup in tups != compare out tups.first_elems




		
		

	
	

