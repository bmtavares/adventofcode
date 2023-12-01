import os
from part1 import *

if __name__ == '__main__':
    puzzle_input = open(os.path.dirname(__file__) + '/puzzleinput.txt','r').read().split('\n')
    puzzle_input.pop()

    stacks = get_stacks(puzzle_input[:puzzle_input.index('')])

    for movement in puzzle_input[puzzle_input.index('')+1:]:
        movement = movement.split()
        move_crates(stacks, int(movement[1]), int(movement[3])-1, int(movement[5])-1, True)

    top_most = ''

    for stack in stacks:
        top_most += stack[-1]

    print(f"The top crates spell the word: {top_most}")
