import os

puzzle_input = open(os.path.dirname(__file__) + '/puzzleinput.txt','r').read().split()

total = 0

for line in puzzle_input:
    pair = line.split(',')

    pair[0] = list(map(int,pair[0].split('-')))
    pair[1] = list(map(int,pair[1].split('-')))

    pair[0] = set(range(pair[0][0],pair[0][1]+1))
    pair[1] = set(range(pair[1][0],pair[1][1]+1))
    
    intersect = len(pair[0].intersection(pair[1]))

    if intersect == len(pair[0]) or intersect == len(pair[1]):
        total += 1

print(f'Total # of complete overlapping tasks is {total}')