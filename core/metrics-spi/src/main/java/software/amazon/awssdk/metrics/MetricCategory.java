/*
 * Copyright 2010-2019 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package software.amazon.awssdk.metrics;

import software.amazon.awssdk.annotations.SdkPublicApi;
import software.amazon.awssdk.metrics.metrics.SdkDefaultMetric;

/**
 * A enum class representing the different types of metric categories in the SDK.
 * <p>
 * A metric can be tagged with multiple categories. Clients can enable/disable metric collection
 * at a {@link MetricCategory} level.
 *
 * @see SdkDefaultMetric
 */
@SdkPublicApi
public enum MetricCategory {

    /**
     * All metrics defined by the SDK are classified under this category at a minimum. If the metrics feature is enabled
     * but the category to collect is not, only metrics that are classified under this category are collected by the SDK
     */
    DEFAULT("default"),

    /**
     * Metrics collected at the http client level are classified under this category.
     */
    HTTP_CLIENT("httpclient"),

    /**
     * Metrics specific to streaming, eventStream APIs are classified under this category.
     */
    STREAMING("streaming"),

    /**
     * This is an umbrella category (provided for convenience) that records metrics belonging to every category
     * defined in this enum. Clients who wish to collect lot of SDK metrics data should use this.
     * <p>
     * Note: Enabling this option is verbose and can be expensive based on the platform the metrics are uploaded to.
     * Please make sure you need all this data before using this category.
     */
    ALL("all")

    ;

    private final String value;

    MetricCategory(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    /**
     * Create a {@link MetricCategory} from the given String value. This method is case insensitive.
     *
     * @param value the value to create the {@link MetricCategory} from
     * @return A {@link MetricCategory} if the given {@link #value} matches one of the enum values.
     *         Otherwise throws {@link IllegalArgumentException}
     */
    public static MetricCategory fromString(String value) {
        for (MetricCategory mc : MetricCategory.values()) {
            if (mc.value.equalsIgnoreCase(value)) {
                return mc;
            }
        }

        throw new IllegalArgumentException("MetricCategory cannot be created from value: " + value);
    }
}
