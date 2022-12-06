import os

def get_start_packet_pos(comm : str, defining_size : int) -> int:
    for i in range(len(comm)):
        if len(set(comm[i:i+defining_size])) == defining_size:
            return i+defining_size
    return -1

if __name__ == '__main__':
    puzzle_input = open(os.path.dirname(__file__) + '/puzzleinput.txt','r').read()
    window_size = 4
    print(max(range(len(puzzle_input)-window_size)))
    print(f"Start of comm is {get_start_packet_pos(puzzle_input, window_size)}")