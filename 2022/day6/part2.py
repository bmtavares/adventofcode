import os
from part1 import get_start_packet_pos

if __name__ == '__main__':
    puzzle_input = open(os.path.dirname(__file__) + '/puzzleinput.txt','r').read()
    window_size = 14
    print(f"Start of comm is {get_start_packet_pos(puzzle_input, window_size)}")