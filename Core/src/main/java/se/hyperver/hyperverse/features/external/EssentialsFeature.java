//
// Hyperverse - A Minecraft world management plugin
//
// This program is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program. If not, see <http://www.gnu.org/licenses/>.
//

package se.hyperver.hyperverse.features.external;

import com.earth2me.essentials.utils.LocationUtil;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;
import se.hyperver.hyperverse.Hyperverse;
import se.hyperver.hyperverse.features.PluginFeature;
import se.hyperver.hyperverse.util.SafeTeleportService;

/**
 * Feature hooking into Essentials
 */
public class EssentialsFeature extends PluginFeature {

    @Override public void initializeFeature() {
        Hyperverse.getApi().registerService(SafeTeleportService.class, new EssentialsSafeTeleportService());
    }

    private static class EssentialsSafeTeleportService implements SafeTeleportService {

        @Override public @NotNull Location findSafeLocation(@NotNull final Location target) {
            try {
                return LocationUtil.getSafeDestination(target);
            } catch (final Exception ignored) {
                return SafeTeleportService.defaultService().findSafeLocation(target);
            }
        }

    }

}
