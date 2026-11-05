"""Comprobación estática sin librerías externas; NO sustituye una compilación."""
from pathlib import Path
import re
import xml.etree.ElementTree as ET

ROOT = Path(__file__).resolve().parents[1]
expected = {
    'app/src/main/AndroidManifest.xml',
    'app/src/main/res/xml/team_widget_info.xml',
    'app/src/main/res/drawable-nodpi/team_widget_horizontal.png',
    'app/src/main/res/drawable-nodpi/team_launcher_icon.png',
    'app/src/main/java/com/aimindset/teamturismo/widget/TeamTurismoWidget.kt',
    'app/src/main/java/com/aimindset/teamturismo/widget/MainActivity.kt',
    'app/src/main/java/com/aimindset/teamturismo/widget/WidgetOpenActivity.kt',
}
for rel in sorted(expected):
    p = ROOT / rel
    assert p.is_file() and p.stat().st_size > 0, f'Falta: {rel}'
for path in ['app/src/main/AndroidManifest.xml','app/src/main/res/xml/team_widget_info.xml']:
    ET.parse(ROOT / path)
widget = (ROOT/'app/src/main/java/com/aimindset/teamturismo/widget/TeamTurismoWidget.kt').read_text()
assert 'R.drawable.team_widget_horizontal' in widget
assert 'actionStartActivity(openIntent)' in widget
assert 'Intent(context, WidgetOpenActivity::class.java)' in widget
activity = (ROOT/'app/src/main/java/com/aimindset/teamturismo/widget/WidgetOpenActivity.kt').read_text()
assert 'https://team-turismo-tulio-ontiveros.vercel.app/compartir-v28/' in activity
assert 'https://team-turismo-tulio-ontiveros.vercel.app/contacto' in activity
build = (ROOT/'build.gradle.kts').read_text()
assert build.count('version "2.3.21"') == 2
for png in ['app/src/main/res/drawable-nodpi/team_widget_horizontal.png','app/src/main/res/drawable-nodpi/team_launcher_icon.png']:
    assert (ROOT/png).read_bytes()[:8] == b'\x89PNG\r\n\x1a\n'
print('OK: archivos, XML, imágenes PNG y enlaces del widget verificados. Sin modificación de la web.')
