package gov.cdc.dex.metadata

import com.google.gson.annotations.SerializedName

data class DEXFileMetadata(
    val version:String = "2.0.0",
    @SerializedName("reporting_jurisdiction") val reportingJurisdiction:String,
    @SerializedName("upload_id") val uploadID: String, //Generated by DEX Ingest service (Upload, Routing Connector)
    val provenance:Provenance, //Chain of Hops the message made to get to CDC/DEX
    @SerializedName("data_stream") val dataStream:DEXDataStream, //DataStream the file belongs to
    @SerializedName("dex_tracing_header") val tracing: DEXTracingHeader, //Tracing Information to track progress of file.

)

/**
 * Data Stream: Additional lower-level categorization of the data.
 * NOT: a Sender, Route, File Format
 *
 *  Example: CELR
 *
 * Notes: Though the relationship is not inherently hierarchical, Data Streams may
 * be conceptually associated with specific Service Lines.
 * Requires some guidance, or a coordinated effort,
 * with DEX to ensure requested data streams terms make sense within the
 * overall paradigm.
 */
data class DEXDataStream(
    @SerializedName("data_stream_id") val dataStreamID: String, //Internally generated ID provided to SENDERS to associate their data with a given DataStream
    @SerializedName("data_stream_route") val dataStreamRoute: String, //Further specialization of the data stream - should it be routed to HL7 pipeline?, CSV?, go straight to EDAV?
    @SerializedName("supporting_metadata") val supportingMetadata: Map<String, String>, //Ancillary metadata that a given data Stream needs to receive in order to properly process data.
    @SerializedName("other_metadata_provided") val otherMetadataProvided: Map<String, String> //Any other metadata provided by sender that is not mapped in DEX.
)

/**
 * Trace - (Industry term)
 *
 * Where in the Data Stream is my data? A trace tracks the timing of operations within a Data Stream,
 * often referred to as "distributed tracing."
 *
 * Traces are "dumb," and just want to know when a span started and ended.
 *
 * Span - (Industry term)
 *
 * A single operation within a trace. Spans are a measure of time.
 * When did I enter the operation and when did I leave.
 * Spans can have children spans.
 * The timing of child spans roll up to their parent's overall span.
 */
data class DEXTracingHeader(
    @SerializedName("trace_id") val traceID: String, //Identifier of the trace to group all tracing for this File.
    @SerializedName("prent_span_id") val parentSpanID: String
)

/**
 * Provenance - Showing what happened to a file as it progresses through a holistic transaction,
 * specifically as moves through DEX, and tracking that files and being able to provide that information.
 *
 * My Definition:
 * Information of where the file came from.
 */
data class Provenance (
    @SerializedName("sender_id") val senderID: String, //ID of the Organization that is sending the data. (Previously onboarded to DEX
    val system: String, //Name of the system providing the data
    @SerializedName("file_path") val filePath: String, //file information on the system indicated above.
    @SerializedName("file_timestamp") val fileTimestamp: String,  //file information on the system indicated above
    @SerializedName("file_size_in_bytes") val fileSize: Long, //file information on the system indicated above.
    @SerializedName("received_from") val receivedFrom: Provenance? //chain of hops the message is going through to get to DEX
)
