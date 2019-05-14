package nl.nkiavl.rharkes;
/* Replace value
(c) 2019 Rolf Harkes Netherlands Cancer Institute.
Basically what it says. Nothing special.

This software is released under the GPL v3. You may copy, distribute and modify 
the software as long as you track changes/dates in source files. Any 
modifications to or software including (via compiler) GPL-licensed code 
must also be made available under the GPL along with build & install instructions.
https://www.gnu.org/licenses/gpl-3.0.en.html

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */

import net.imglib2.Cursor;
import net.imglib2.img.Img;
import net.imglib2.type.numeric.NumericType;

import org.scijava.app.StatusService;
import org.scijava.command.Command;
import org.scijava.log.LogService;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;

/**
 * Replaces a value in an image with another value
 * 
 */
@Plugin(type = Command.class, headless = true,
menuPath = "Plugins>Process>Replace Value")
public class ReplaceValues<T extends NumericType <T>> implements Command{

	@Parameter
	private LogService log;

	@Parameter
	private StatusService statusService;

	@Parameter(label = "Select image", description = "the image field")
	private Img<T> img;

	@Parameter(label = "Replace", description = "Value to replace")
	private Img<T> replace;

	@Parameter(label = "Replace with", description = "Value to replace with")
	private Img<T> replaceWith;

	public static void main(final String... args) throws Exception {

	}

	@Override
	public void run() {
		Cursor<T> cursor = img.cursor();
		T rep = replace.firstElement();
		T repW = replaceWith.firstElement();
		log.info("Replacing "+rep.toString()+" with "+repW.toString());
		//go over all pixels to see what values exist
		if (statusService != null) {statusService.showStatus("Replacing...");}
		while (cursor.hasNext()) {
			cursor.fwd(); //starts at -1
			T t = cursor.get();
			if (t.valueEquals(rep)) {
				t.set(repW);
			}
		}
		if (statusService != null) {statusService.showStatus("Finished...");}
	}
}