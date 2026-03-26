#!/usr/bin/env python3
from pathlib import Path

ROOT = Path(r"/Users/ekko/.m2/repository/org/openjfx")
PLATFROM = "mac"
VERSION = "21.0.5"

modules = [
    "javafx-base",
    "javafx-graphics",
    "javafx-controls",
    "javafx-fxml"
]

jars = []

for m in modules:
    dir = ROOT / m / VERSION
    for f in dir.iterdir():
        path = str(f)

        if "mac" in path and f.suffix == ".jar":
            jars.append(path)

module_path = ";".join(jars)

vm = (
    f'--module-path "{module_path}"'
    f'--add-modules javafx.controls,javafx.fxml'
)

print(vm)
