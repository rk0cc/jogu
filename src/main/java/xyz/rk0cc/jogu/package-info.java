/**
 * <h2>Java object of Git repository URL.</h2>
 *
 * This library follows Git's supported protocol for locating remote repository:
 * <ul>
 *     <li>Git protocol</li>
 *     <li>HTTPS protocol</li>
 *     <li>
 *         <b>SSH protocol:</b>
 *         <ul style="margin-top: 5px; margin-bottom: 5px;">
 *             <li>Traditional</li>
 *             <li>Alternative</li>
 *         </ul>
 *     </li>
 * </ul>
 * (Only 'File' protocol is not included in this package since it just crawling local package only and not recommended
 * to uses)
 *
 * @since 1.0.0
 *
 * @see <a href="https://git-scm.com/book/en/v2/Git-on-the-Server-The-Protocols">Official documentation about Git on server protocol</a>
 */
package xyz.rk0cc.jogu;