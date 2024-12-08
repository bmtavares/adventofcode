import requests
import os
import sys
import argparse
from time import sleep
from datetime import datetime

"""
Puzzle Elf, my tool to manage the aoc repo

(Because "Why didn't I just use advent-of-code-data" was too long)
"""

ENV_KEY = "AOC_TOKEN"
ENV_FILE = f".{ENV_KEY.lower()}"
SCRIPT_DIR = os.path.dirname(os.path.abspath(__file__))
HEADERS = {
    "User-Agent": "https://github.com/bmtavares/adventofcode"  # as per https://old.reddit.com/r/adventofcode/comments/z9dhtd/please_include_your_contact_info_in_the_useragent/
}
AOC_INPUT_URL = "https://adventofcode.com/{year}/day/{day}/input"
TODAY = datetime.today()
CHRISTMAS = 25
FIRST_AOC_YEAR = 2015
PUZZLE_INPUT_FILENAME = "puzzleinput.txt"
YEAR_FOLDER_NAME = "{year}"
DAY_FOLDER_NAME = "day{day:02}"
# Time to wait between each request when populating a full year
# Because nobody wants coal in their stockings
NICENESS_SLEEP_IN_SEC = 3


# Thank you gareth @ https://codegolf.stackexchange.com/questions/4707/outputting-ordinal-numbers-1st-2nd-3rd#answer-4712
ordinal = lambda n: "%d%s" % (
    n,
    "tsnrhtdd"[(n // 10 % 10 != 1) * (n % 10 < 4) * n % 10 :: 4],
)


def get_token(argument_input: str) -> str | None:
    if argument_input:
        print("Using token passed via --token")
        return argument_input
    env_var = os.environ.get(ENV_KEY)
    if env_var:
        print(f"Using token found in environment variable {ENV_KEY}")
        return env_var
    else:
        print(f"Using token found in file {ENV_FILE}")
        with open(f"{ENV_FILE}") as f:
            return f.readline()


def create_folder(day: int, year: int) -> None:
    path = f"{SCRIPT_DIR}/../{YEAR_FOLDER_NAME.format(year=year)}/{DAY_FOLDER_NAME.format(day=day)}"
    os.makedirs(path, exist_ok=True)
    return path


def create_input_file(
    path: str, data: str, filename: str = PUZZLE_INPUT_FILENAME, overwrite: bool = True
) -> None:
    access: str
    if overwrite:
        access = "w"
    else:
        access = "x"
    try:
        with open(file=f"{path}/{filename}", mode=access) as f:
            f.write(data)
    except FileExistsError:
        pass


def aoc_input_request(token: str, day: int, year: int) -> str:
    url = AOC_INPUT_URL.format(day=day, year=year)
    cookies = {"session": token}
    req = requests.get(url=url, headers=HEADERS, cookies=cookies)
    return req.text


def create_parser() -> argparse.ArgumentParser:
    parser = argparse.ArgumentParser()
    parser.add_argument("--day", type=int, help="Puzzle day")
    parser.add_argument("--year", type=int, help="Puzzle year")
    parser.add_argument(
        "--populate", action="store_true", help="Use to populate a whole year"
    )
    parser.add_argument(
        "--placeholder",
        action="store_true",
        help="Use to create the file structure without requests to AOC",
    )
    parser.add_argument(
        "--token",
        type=str,
        help="AOC session token (has priority over environment variable and local dotfile)",
    )
    return parser


def progress_visual_as_string(
    total: int,
    done: int = sys.maxsize,
    working: bool = True,
    done_char: str = "■",
    in_prog_char: str = "▪",
    not_done_char: str = "□",
) -> str:
    if done >= total:
        return f"{done_char * total}"
    return f"{done_char * done}{in_prog_char if working else not_done_char}{not_done_char * (total - 1 - done)}"


def handle_populate(token: str, year: int, placeholder: bool = False) -> None:
    last_day: int = CHRISTMAS
    if year == TODAY.year and TODAY.day < CHRISTMAS:
        last_day = TODAY.day
    if last_day == 1:
        print(
            f"Target day is... the 1st of December (the month just started, why are we trying to fetch everything?)"
        )
    else:
        print(f"Target days are December 1st through {ordinal(last_day)}")
    if not placeholder:
        print(f"Sleeping for {NICENESS_SLEEP_IN_SEC} seconds between each request")
    for i in range(1, last_day + 1):
        print(
            f"\r{i:02}/{last_day:02} : {progress_visual_as_string(total=last_day,done=i-1)} : Working ",
            end="",
            flush=True,
        )
        handle_day(token=token, day=i, year=year, placeholder=placeholder)
        if not placeholder and i != last_day:
            print(
                f"\r{i:02}/{last_day:02} : {progress_visual_as_string(total=last_day,done=i,working=False)} : Sleeping",
                end="",
                flush=True,
            )
            sleep(NICENESS_SLEEP_IN_SEC)
    print(
        f"\r{i:02}/{last_day:02} : {progress_visual_as_string(total=last_day)} : Done{' ' * 4}",
        flush=True,
    )


def handle_day(token: str, day: int, year: int, placeholder: bool = False) -> None:
    path = create_folder(day, year)
    if placeholder:
        create_input_file(path=path, data="", overwrite=False)
    else:
        puzzle_data = aoc_input_request(token=token, day=day, year=year)
        create_input_file(path=path, data=puzzle_data)


def get_year(arg_year: int) -> int:
    if not arg_year or arg_year < FIRST_AOC_YEAR or arg_year > TODAY.year:
        return TODAY.year
    return arg_year


def get_day(arg_day: int) -> int:
    if not arg_day or arg_day > 25:
        if TODAY.day <= 25:
            return TODAY.day
        return 1
    else:
        return arg_day


def main(args) -> None:
    token = get_token(args.token)
    if not token:
        print(
            f"AOC token not found. Please set it as {ENV_KEY} environment variable, in a file called {ENV_FILE} or via --token.\nYou can get the token using your browsers' devtools and checking the session cookie for AOC."
        )
        exit()
    year = get_year(args.year)
    if args.populate:
        print(
            f"Populating {year} with {'placeholders' if args.placeholder else 'puzzle inputs'}"
        )
        handle_populate(token=token, year=year, placeholder=args.placeholder)
    else:
        day = get_day(args.day)
        print(f"Fetching puzzle input for {day:02}/{year}")
        handle_day(token=token, day=day, year=year, placeholder=args.placeholder)


if __name__ == "__main__":
    args = create_parser().parse_args()
    main(args)
