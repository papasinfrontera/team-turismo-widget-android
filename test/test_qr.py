"""Pruebas independientes de QR; ejecutar: python test/test_qr.py"""
from pathlib import Path
import cv2
from PIL import Image
BASE = Path(__file__).resolve().parents[1]
URL = "https://team-turismo-tulio-ontiveros.vercel.app/contacto"
paths = [BASE / "qr_team_turismo_contacto.png", BASE / "app/src/main/res/drawable-nodpi/team_widget_horizontal.png"]
for p in paths:
    original = Image.open(p).convert("RGB")
    for scale in [1.0, 0.65, 0.5]:
        image = original.resize((int(original.width*scale), int(original.height*scale)), Image.Resampling.LANCZOS)
        import numpy as np
        bgr = cv2.cvtColor(np.array(image), cv2.COLOR_RGB2BGR)
        text, points, _ = cv2.QRCodeDetector().detectAndDecode(bgr)
        assert text == URL, (p, scale, repr(text))
        print(f"PASS {p.name} × {scale}: {URL}")
print("OK: QR funcional en todas las escalas probadas")
