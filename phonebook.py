import os

os.system("")  # Windows hack


class Style:
    RED = '\033[31m'
    BLUE = '\033[34m'
    RESET = '\033[0m'


db = {}  # Initialize database


def __colored(s: str, color: str = Style.BLUE) -> str:
    return f"{color}{s}{Style.RESET}"


def __cmd_loop():
    while True:
        print("Enter action:\n"
              "\tc(reate) name number\n"
              "\tr(ead) [name]\n"
              "\tu(pdate) name number\n"
              "\td(delete) name\n")
        command = input()
        try:
            __run_cmd(command)
        except BaseException as err:
            print(__colored(f"Action failed: {err}", Style.RED))


def __run_cmd(command: str):
    cmd, *tail = command.split(" ")

    if 'create'.startswith(cmd):
        __create(*tail)
    elif 'read'.startswith(cmd):
        if tail:
            __read(tail[0])
        else:
            __read_all()
    elif 'update'.startswith(cmd):
        name, number = tail
        __update(name, number)
    elif 'delete'.startswith(cmd):
        __delete(tail[0])
    else:
        raise ValueError("wrong verb")


def __create(name: str, number: str):
    if name in db:
        raise ValueError(f"Record for '{name}' already exists!")
    else:
        db[name] = number
        print(__colored(f"Record created - {name}: {number}"))


def __read(name: str):
    if name in db:
        number = db[name]
        print(__colored(f"Record retrieved - {name}: {number}"))
    else:
        raise ValueError(f"Record for '{name}' does not exist!")


def __read_all():
    print(__colored(str(db)))


def __update(name: str, number: str):
    db[name] = number
    print(__colored(f"Record updated - {name}: {number}"))


def __delete(name: str):
    db.pop(name, None)
    print(__colored(f"Record '{name}' deleted"))


__cmd_loop()
