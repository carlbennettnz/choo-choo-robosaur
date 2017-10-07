package canvas;

import common.AABB;
import common.GameController;
import common.Renderable;
import common.Vector;

import org.junit.Test;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

import static org.mockito.Mockito.*;

public class CanvasTest {
	@Test
	public void drawGameTitle() throws Exception {
		GameController controller = mock(GameController.class);
		Graphics g = mock(Graphics.class);
		Canvas canvas = spy(new Canvas(controller));

		canvas.drawGameTitle(g);

		verify(canvas).drawTitle(g, "Choo Choo Robosaur");
	}

	@Test
	public void drawLevelTitle() throws Exception {
		GameController controller = mock(GameController.class);
		Graphics g = mock(Graphics.class);
		Canvas canvas = spy(new Canvas(controller));

		when(controller.getLevel()).thenReturn(1);

		canvas.drawLevelTitle(g);

		verify(canvas).drawTitle(g, "Level 1");
	}

	@Test
	public void drawEndScreen() throws Exception {
		GameController controller = mock(GameController.class);
		Graphics g = mock(Graphics.class);
		Canvas canvas = spy(new Canvas(controller));

		canvas.drawEndScreen(g);

		verify(canvas).drawTitle(g, "End");
	}

	@Test
	public void drawTitle() throws Exception {
		GameController controller = mock(GameController.class);
		Graphics g = mock(Graphics.class);
		Canvas canvas = spy(new Canvas(controller));

		when(canvas.getSize()).thenReturn(new Dimension(100, 100));

		canvas.drawTitle(g, "Title");

		verify(g).setColor(Color.white);
		verify(g).drawChars("Title".toCharArray(), 0, 5, 20, 50);
	}

	@Test
	public void drawGame() throws Exception {
		GameController controller = mock(GameController.class);
		Graphics g = mock(Graphics.class);
		Canvas canvas = spy(new Canvas(controller));

		List<Renderable> entities = new ArrayList<>();
		Renderable e1 = mock(Renderable.class);
		Renderable e2 = mock(Renderable.class);
		entities.add(e1);
		entities.add(e2);

		doReturn(entities).when(canvas).getEntitiesInView();

		canvas.drawGame(g);

		verify(e1).draw(g);
		verify(e2).draw(g);
	}

	@Test
	public void update() {
		GameController controller = mock(GameController.class);
		Graphics g = mock(Graphics.class);
		Canvas canvas = spy(new Canvas(controller));
		Image image = mock(Image.class);
		Graphics g2 = image.getGraphics();

		doNothing().when(canvas).paint(g2);
		doReturn(image).when(canvas).getOffscreenImage();

		canvas.update(g);

		verify(canvas).paint(g2);
		verify(g).drawImage(image, 0, 0, canvas);
	}
}
