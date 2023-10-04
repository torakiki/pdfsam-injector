/* 
 * This file is part of the PDF Split And Merge source code
 * Created on 6 feb 2021
 * Copyright 2020 by Sober Lemur S.r.l. (info@pdfsam.org).
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 * 
 * http://www.apache.org/licenses/LICENSE-2.0 
 * 
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License. 
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
/**
 * @author Andrea Vacondio
 *
 */
module org.pdfsam.injector {
    exports org.pdfsam.injector;

    requires jakarta.inject;
    requires org.slf4j;
}