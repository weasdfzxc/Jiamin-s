// The DrawLinesTouch script adapted to work with mouse input
using Vectrosity;
using UnityEngine;
using System.Collections;
public class DrawLine : MonoBehaviour
{
    public Material lineMaterial;
    public int maxPoints = 5000;
    public float lineWidth = 10.0f;
    public int minPixelMove = 5;	// Must move at least this many pixels per sample for a new segment to be recorded
    public bool useEndCap = false;
    public Texture2D capTex;
    public Material capMaterial;
    public int maxStroke;
    public GameObject plane;

    private string color;
    private Vector2[][] linePoints;
    private VectorLine [] line;
    private int lineIndex = 0;
    private Vector2 previousPosition;
    private int sqrMinPixelMove;
    private bool canDraw = false;
    private int i;
    private WCamera wcamera;
    private WebCamTexture webCamera;
    private bool ok;
    public Color blue;
    public Color gray;
    public Color orange;
    public Color black;
    public Color white;
    public Color ayano;
    public Color yellow;
    public Color green;
    public Color ock;

    public void Start()
    {

        
        if (useEndCap)
        {
            VectorLine.SetEndCap("cap", EndCap.Mirror, capMaterial, capTex);
            lineMaterial = capMaterial;
        }
        linePoints = new Vector2[ maxStroke][];
        line = new VectorLine[maxStroke];
        for (i = 0; i < maxStroke; i++)
        {
            linePoints[i] = new Vector2[maxPoints];
            line[i] = new VectorLine("DrawnLine" + i, linePoints[i], lineMaterial, lineWidth, LineType.Continuous, Joins.Weld);
        }
        i = 0;
    
        if (useEndCap)
        {
            line[i].endCap = "cap";
        }
        sqrMinPixelMove = minPixelMove * minPixelMove;
    }

    public void Update()
    {
        if (Input.GetKeyDown(KeyCode.Escape))
        {
            for (int o = 0; o < line.Length; o++)
            {

                Destroy(GameObject.Find("Vector DrawnLine" + o));
            }
        }
        if (webCamera == null)
        {
            if (plane != null)
            {
                plane.GetComponent<WCamera>();
                wcamera = plane.GetComponent<WCamera>();
                webCamera = wcamera.webCamera;
                /*blue = new Color(69 / 255, 178 / 255, 1, wcamera.alpha);
                gray = new Color(80 / 255, 80 / 255, 80 / 255, wcamera.alpha);
                orange = new Color(1, 110 / 255, 70 / 255, wcamera.alpha);
                black = new Color(0, 0, 0, wcamera.alpha);
                white = new Color(1, 1, 1, wcamera.alpha);
                ayano = new Color(1, 110 / 255, 110 / 255, wcamera.alpha);
                yellow = new Color(1, 1, 50 / 255, wcamera.alpha);
                green = new Color(175 / 255, 235 / 255, 50 / 255, wcamera.alpha);
                ock = new Color(180 / 255, 90 / 255, 1, wcamera.alpha); */
                blue.a = wcamera.alpha;
                gray.a = wcamera.alpha;
                orange.a = wcamera.alpha;
                black.a = wcamera.alpha;
                white.a = wcamera.alpha;
                ayano.a = wcamera.alpha;
                yellow.a = wcamera.alpha;
                green.a = wcamera.alpha;
                ock.a = wcamera.alpha;
            }

            
        }
        else
        {
            blue.a = wcamera.alpha;
            gray.a = wcamera.alpha;
            orange.a = wcamera.alpha;
            black.a = wcamera.alpha;
            white.a = wcamera.alpha;
            ayano.a = wcamera.alpha;
            yellow.a = wcamera.alpha;
            green.a = wcamera.alpha;
            ock.a = wcamera.alpha;
            color = wcamera.stringToEdit;
            if (wcamera.lineWidth != 0)
            {
                line[i].lineWidth = wcamera.lineWidth;
            }
            else
            {
                line[i].lineWidth = 5.0f;
            }
            if (webCamera.isPlaying != true)
            {
                ok = true;
                Vector2 mousePos = Input.mousePosition;

                if (Input.GetMouseButtonDown(0) && !(mousePos.x < 200 && mousePos.y < 40) && mousePos.y > Screen.height / 9)
                {
                    //line[i].ZeroPoints();
                    //line[i].minDrawIndex = 0;
                    //line[i].Draw();
                    i++;
                    previousPosition = linePoints[i][0] = mousePos;
                    lineIndex = 0;
                    canDraw = true;
                }
                else if (Input.GetMouseButton(0) && (mousePos - previousPosition).sqrMagnitude > sqrMinPixelMove && canDraw && !(mousePos.x < 200 && mousePos.y < 40) && mousePos.y > Screen.height / 9)
                {
                    previousPosition = linePoints[i][++lineIndex] = mousePos;
                    line[i].minDrawIndex = lineIndex - 1;
                    line[i].maxDrawIndex = lineIndex;
                    if (useEndCap) line[i].drawEnd = lineIndex;
                    if (lineIndex >= maxPoints - 1) canDraw = false;
                    line[i].Draw();
                    line[i].SetColor(Color.white);
                    switch (color)
                    {
                        case "white": line[i].SetColor(white); break;
                        case "black": line[i].SetColor(black); break;
                        case "blue": line[i].SetColor(blue); break;
                        case "red": line[i].SetColor(yellow); break;
                        case "gray": line[i].SetColor(gray); break;
                        case "orange": line[i].SetColor(orange); break;
                        case "ayano": line[i].SetColor(ayano); break;
                        case "green": line[i].SetColor(green); break;
                        case "ock": line[i].SetColor(ock); break;
                    }
                    
                    switch (color)
                    {
                        case "white": wcamera.settext(white); break;
                        case "black": wcamera.settext(black); break;
                        case "blue": wcamera.settext(blue); break;
                        case "red": wcamera.settext(yellow); break;
                        case "gray": wcamera.settext(gray); break;
                        case "orange": wcamera.settext(orange); break;
                        case "ayano": wcamera.settext(ayano); break;
                        case "green": wcamera.settext(green); break;
                        case "ock": wcamera.settext(ock); break;
                   }

                }
                //for (int n = 2; n < line.Length; n++)
                //{
                //    line[n].Draw();
                //}
            }
                else
                {
                     if (ok)
                    {
                        //Debug.Log("clear lines");
                        StartCoroutine(ClearLines());
                        ok = false;
                    }
                 }
            }
     }
    IEnumerator ClearLines()
    {
        yield return new WaitForEndOfFrame();
        for(int o=0;o<line.Length;o++)
        {

            Destroy(GameObject.Find("Vector DrawnLine"+o));
        }
        for (i = 0; i < maxStroke; i++)
        {
            linePoints[i] = new Vector2[maxPoints];
            line[i] = new VectorLine("DrawnLine" + i, linePoints[i], lineMaterial, lineWidth, LineType.Continuous, Joins.Weld);
        }
        i = 0;
    }
}
